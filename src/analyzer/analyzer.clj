(ns analyzer.analyzer
  (:require [analyzer.utils :as u]
            [analyzer.env :as env :refer [*env*]]
            [clojure.reflect :as reflect]
            [clojure.core.memoize :refer [memo-clear! lru]]
            [analyzer.passes.source-info :refer [source-info]]
            [analyzer.passes.warn-on-reflection :refer [warn-on-reflection]]
            [analyzer.passes.elide-meta :refer [elide-meta elides]]
            [analyzer.passes.warn-earmuff :refer [warn-earmuff]]
            [analyzer.uniquify :refer [uniquify-locals]])
  (:import (clojure.lang IObj RT Compiler Var Symbol IPersistentVector IPersistentMap IPersistentSet ISeq IType IRecord)))

(deftype ExceptionThrown [e ast])

(defn ^:private throw! [e]
  (throw (.e ^ExceptionThrown e)))

(defn empty-env
  "Returns an empty env map"
  []
  {:context    :ctx/expr
   :locals     {}
   :ns         (ns-name *ns*)})

(defn build-ns-map []
  (into {} (mapv #(vector (ns-name %)
                          {:mappings (merge (ns-map %) {'in-ns #'clojure.core/in-ns
                                                        'ns    #'clojure.core/ns})
                           :aliases  (reduce-kv (fn [a k v] (assoc a k (ns-name v)))
                                                {} (ns-aliases %))
                           :ns       (ns-name %)})
                 (all-ns))))

(defn global-env []
  (atom {:namespaces     (build-ns-map)

         :update-ns-map! (fn update-ns-map! []
                           (swap! *env* assoc-in [:namespaces] (build-ns-map)))}))

(defn update-ns-map! []
  ((get (env/deref-env) :update-ns-map! #())))

(defn qualify-arglists [arglists]
  (vary-meta arglists merge
             (when-let [t (:tag (meta arglists))]
               {:tag (if (or (string? t)
                             (u/specials (str t))
                             (u/special-arrays (str t)))
                       t
                       (if-let [c (u/maybe-class t)]
                         (let [new-t (-> c .getName symbol)]
                           (if (= new-t t)
                             t
                             (with-meta new-t {::qualified? true})))
                         t))})))

(defn create-var
  "Creates a Var for sym and returns it.
   The Var gets interned in the env namespace."
  [sym {:keys [ns]}]
  (let [v (get-in (env/deref-env) [:namespaces ns :mappings (symbol (name sym))])]
    (if (and v (or (class? v)
                   (= ns (ns-name (.ns ^Var v)))))
      v
      (let [meta (dissoc (meta sym) :inline :inline-arities :macro)
            meta (if-let [arglists (:arglists meta)]
                   (assoc meta :arglists (qualify-arglists arglists))
                   meta)]
        (intern ns (with-meta sym meta))))))

(defmulti -analyze-form (fn [form _] (class form)))

(declare analyze-symbol
         analyze-vector
         analyze-map
         analyze-set
         analyze-seq
         analyze-const)

(def ^:dynamic analyze-form
  "Like analyze, but does not mark the form with :top-level true"
  -analyze-form)

(defmethod -analyze-form Symbol
  [form env]
  (analyze-symbol form env))

(defmethod -analyze-form IPersistentVector
  [form env]
  (analyze-vector form env))

(defmethod -analyze-form IPersistentMap
  [form env]
  (analyze-map form env))

(defmethod -analyze-form IPersistentSet
  [form env]
  (analyze-set form env))

(defmethod -analyze-form ISeq
  [form env]
  (if-let [form (seq form)]
    (analyze-seq form env)
    (analyze-const form env)))

(defmethod -analyze-form IType
  [form env]
  (analyze-const form env :type))

(prefer-method -analyze-form IType IPersistentMap)
(prefer-method -analyze-form IType IPersistentVector)
(prefer-method -analyze-form IType IPersistentSet)
(prefer-method -analyze-form IType ISeq)

(defmethod -analyze-form IRecord
  [form env]
  (analyze-const form env :record))

(prefer-method -analyze-form IRecord IPersistentMap)
(prefer-method -analyze-form IRecord IPersistentVector)
(prefer-method -analyze-form IRecord IPersistentSet)
(prefer-method -analyze-form IRecord ISeq)

(defmethod -analyze-form :default
  [form env]
  (analyze-const form env))

(defn -analyze
  "Given a form to analyze and an environment, a map containing:
   * :locals     a map from binding symbol to AST of the binding value
   * :context    a keyword describing the form's context from the :ctx/* hierarchy.
    ** :ctx/expr      the form is an expression: its value is used
    ** :ctx/return    the form is an expression in return position, derives :ctx/expr
    ** :ctx/statement the value of the form is not used
   * :ns         a symbol representing the current namespace of the form to be
                 analyzed

   returns an AST for that form.

   Every node in the AST is a map that is *guaranteed* to have the following keys:
   * :op   a keyword describing the AST node
   * :form the form represented by the AST node
   * :env  the environment map of the AST node

   Additionally if the AST node contains sub-nodes, it is guaranteed to have:
   * :children a vector of the keys of the AST node mapping to the sub-nodes,
               ordered, when that makes sense

   It is considered a node either the top-level node (marked with :top-level true)
   or a node that can be reached via :children; if a node contains a node-like
   map that is not reachable by :children, there's no guarantee that such a map
   will contain the guaranteed keys."

  [form env]
  (assoc (analyze-form form env) :top-level true))

(defn parse-monitor-enter
  [[_ target :as form] env]
  (when-not (= 2 (count form))
    (throw (ex-info (str "Wrong number of args to monitor-enter, had: " (dec (count form)))
                    (merge {:form form}
                           (u/-source-info form env)))))
  {:op       :monitor-enter
   :env      env
   :form     form
   :target   (-analyze target (u/ctx env :ctx/expr))
   :children [:target]})

(defn parse-monitor-exit
  [[_ target :as form] env]
  (when-not (= 2 (count form))
    (throw (ex-info (str "Wrong number of args to monitor-exit, had: " (dec (count form)))
                    (merge {:form form}
                           (u/-source-info form env)))))
  {:op       :monitor-exit
   :env      env
   :form     form
   :target   (-analyze target (u/ctx env :ctx/expr))
   :children [:target]})

(defn parse-import*
  [[_ class :as form] env]
  (when-not (= 2 (count form))
    (throw (ex-info (str "Wrong number of args to import*, had: " (dec (count form)))
                    (merge {:form form}
                           (u/-source-info form env)))))
  {:op    :import
   :env   env
   :form  form
   :class class})

(defn valid-binding-symbol? [s]
  (and (symbol? s)
       (not (namespace s))
       (not (re-find #"\." (name s)))))

(declare parse)

(defn analyze-body [body env]
  ;; :body is used by emit-form to remove the artificial 'do
  (assoc (parse (cons 'do body) env) :body? true))

(defn analyze-fn-method [[params & body :as form] {:keys [locals local] :as env}]
  (when-not (vector? params)
    (throw (ex-info "Parameter declaration should be a vector"
                    (merge {:params params
                            :form   form}
                           (u/-source-info form env)
                           (u/-source-info params env)))))
  (when (not-every? valid-binding-symbol? params)
    (throw (ex-info (str "Params must be valid binding symbols, had: "
                         (mapv class params))
                    (merge {:params params
                            :form   form}
                           (u/-source-info form env)
                           (u/-source-info params env))))) ;; more specific
  (let [variadic? (boolean (some '#{&} params))
        params-names (if variadic? (conj (pop (pop params)) (peek params)) params)
        env (dissoc env :local)
        arity (count params-names)
        params-expr (mapv (fn [name id]
                            {:env       env
                             :form      name
                             :name      name
                             :variadic? (and variadic?
                                             (= id (dec arity)))
                             :op        :binding
                             :arg-id    id
                             :local     :arg})
                          params-names (range))
        fixed-arity (if variadic?
                      (dec arity)
                      arity)
        loop-id (gensym "loop_")
        body-env (into (update-in env [:locals]
                                  merge (zipmap params-names (map u/dissoc-env params-expr)))
                       {:context     :ctx/return
                        :loop-id     loop-id
                        :loop-locals (count params-expr)})
        body (analyze-body body body-env)]
    (when variadic?
      (let [x (drop-while #(not= % '&) params)]
        (when (contains? #{nil '&} (second x))
          (throw (ex-info "Invalid parameter list"
                          (merge {:params params
                                  :form   form}
                                 (u/-source-info form env)
                                 (u/-source-info params env)))))
        (when (not= 2 (count x))
          (throw (ex-info (str "Unexpected parameter: " (first (drop 2 x))
                               " after variadic parameter: " (second x))
                          (merge {:params params
                                  :form   form}
                                 (u/-source-info form env)
                                 (u/-source-info params env)))))))
    (merge
     {:op          :fn-method
      :form        form
      :loop-id     loop-id
      :env         env
      :variadic?   variadic?
      :params      params-expr
      :fixed-arity fixed-arity
      :body        body
      :children    [:params :body]}
     (when local
       {:local (u/dissoc-env local)}))))

(defn analyze-method-impls
  [[method [this & params :as args] & body :as form] env]
  (when-let [error-msg (cond
                         (not (symbol? method))
                         (str "Method method must be a symbol, had: " (class method))
                         (not (vector? args))
                         (str "Parameter listing should be a vector, had: " (class args))
                         (not (first args))
                         (str "Must supply at least one argument for 'this' in: " method))]
    (throw (ex-info error-msg
                    (merge {:form     form
                            :in       (:this env)
                            :method   method
                            :args     args}
                           (u/-source-info form env)))))
  (let [meth        (cons (vec params) body) ;; this is an implicit arg
        this-expr   {:name  this
                     :env   env
                     :form  this
                     :op    :binding
                     :o-tag (:this env)
                     :tag   (:this env)
                     :local :this}
        env         (assoc-in (dissoc env :this) [:locals this] (u/dissoc-env this-expr))
        method-expr (analyze-fn-method meth env)]
    (assoc (dissoc method-expr :variadic?)
           :op       :method
           :form     form
           :this     this-expr
           :name     (symbol (name method))
           :children (into [:this] (:children method-expr)))))

(defn ^:private type-reflect
  [typeref & options]
  (apply reflect/type-reflect typeref
         :reflector (reflect/->JavaReflector (RT/baseLoader))
         options))

(def object-members
  (:members (type-reflect Object)))

(defn ^Class box
  "If the argument is a primitive Class, returns its boxed equivalent,
   otherwise returns the argument"
  [c]
  ({Integer/TYPE   Integer
    Float/TYPE     Float
    Double/TYPE    Double
    Long/TYPE      Long
    Character/TYPE Character
    Short/TYPE     Short
    Byte/TYPE      Byte
    Boolean/TYPE   Boolean
    Void/TYPE      Void}
   c c))

(def members*
  (lru (fn members*
         ([class]
          (into object-members
                (remove (fn [{:keys [flags]}]
                          (not-any? #{:public :protected} flags))
                        (-> class
                            u/maybe-class
                            ^Class (box)
                            .getName
                            symbol
                            (type-reflect :ancestors true)
                            :members)))))))

;; HACK
(defn -deftype [name class-name args interfaces]

  (doseq [arg [class-name name]]
    (memo-clear! members* [arg])
    (memo-clear! members* [(str arg)]))

  (let [interfaces (mapv #(symbol (.getName ^Class %)) interfaces)]
    (eval (list `let []
                (list 'deftype* name class-name args :implements interfaces)
                (list `import class-name)))))

;; this node wraps non-quoted collections literals with metadata attached
;; to them, the metadata will be evaluated at run-time, not treated like a constant
(defn wrapping-meta
  [{:keys [form env] :as expr}]
  (let [meta (meta form)]
    (if (and (u/obj? form)
             (seq meta))
      {:op       :with-meta
       :env      env
       :form     form
       :meta     (analyze-form meta (u/ctx env :ctx/expr))
       :expr     (assoc-in expr [:env :context] :ctx/expr)
       :children [:meta :expr]}
      expr)))

(defn parse-reify*
  [[_ interfaces & methods :as form] env]
  (let [interfaces (conj (disj (set (mapv u/maybe-class interfaces)) Object)
                         IObj)
        name (gensym "reify__")
        class-name (symbol (str (namespace-munge *ns*) "$" name))
        menv (assoc env :this class-name)
        methods (mapv #(assoc (analyze-method-impls % menv) :interfaces interfaces)
                      methods)]

    (-deftype name class-name [] interfaces)

    (wrapping-meta
     {:op         :reify
      :env        env
      :form       form
      :class-name class-name
      :methods    methods
      :interfaces interfaces
      :children   [:methods]})))

(defn parse-opts+methods [methods]
  (loop [opts {} methods methods]
    (if (keyword? (first methods))
      (recur (assoc opts (first methods) (second methods)) (nnext methods))
      [opts methods])))

(defn parse-deftype*
  [[_ name class-name fields _ interfaces & methods :as form] env]
  (let [interfaces (disj (set (mapv u/maybe-class interfaces)) Object)
        fields-expr (mapv (fn [name]
                            {:env     env
                             :form    name
                             :name    name
                             :mutable (let [m (meta name)]
                                        (or (and (:unsynchronized-mutable m)
                                                 :unsynchronized-mutable)
                                            (and (:volatile-mutable m)
                                                 :volatile-mutable)))
                             :local   :field
                             :op      :binding})
                          fields)
        menv (assoc env
                    :context :ctx/expr
                    :locals  (zipmap fields (map u/dissoc-env fields-expr))
                    :this    class-name)
        [opts methods] (parse-opts+methods methods)
        methods (mapv #(assoc (analyze-method-impls % menv) :interfaces interfaces)
                      methods)]

    (-deftype name class-name fields interfaces)

    {:op         :deftype
     :env        env
     :form       form
     :name       name
     :class-name class-name ;; internal, don't use as a Class
     :fields     fields-expr
     :methods    methods
     :interfaces interfaces
     :children   [:fields :methods]}))


(defn parse-case*
  [[_ expr shift mask default case-map switch-type test-type & [skip-check?] :as form] env]
  (let [[low high] ((juxt first last) (keys case-map)) ;;case-map is a sorted-map
        e (u/ctx env :ctx/expr)
        test-expr (-analyze expr e)
        [tests thens] (reduce (fn [[te th] [min-hash [test then]]]
                                (let [test-expr (ana/analyze-const test e)
                                      then-expr (-analyze then env)]
                                  [(conj te {:op       :case-test
                                             :form     test
                                             :env      e
                                             :hash     min-hash
                                             :test     test-expr
                                             :children [:test]})
                                   (conj th {:op       :case-then
                                             :form     then
                                             :env      env
                                             :hash     min-hash
                                             :then     then-expr
                                             :children [:then]})]))
                              [[] []] case-map)
        default-expr (-analyze default env)]
    {:op          :case
     :form        form
     :env         env
     :test        (assoc test-expr :case-test true)
     :default     default-expr
     :tests       tests
     :thens       thens
     :shift       shift
     :mask        mask
     :low         low
     :high        high
     :switch-type switch-type
     :test-type   test-type
     :skip-check? skip-check?
     :children    [:test :tests :thens :default]}))

(defn parse
  "Extension to tools.analyzer/-parse for JVM special forms"
  [form env]
  ((case (first form)
     monitor-enter        parse-monitor-enter
     monitor-exit         parse-monitor-exit
     clojure.core/import* parse-import*
     reify*               parse-reify*
     deftype*             parse-deftype*
     case*                parse-case*
     #_:else              ana/-parse)
   form env))



























(def ^:dynamic elides
  "A map of op keywords to predicate IFns.
   The predicate will be used to indicate what map keys should be elided on
   metadata of nodes for that op.
   :all can be used to indicate what should be elided for every node with
   metadata.
   Defaults to {:all (set (:elide-meta *compiler-options*))}"
  {:all (set (:elide-meta *compiler-options*))})

(def default-passes-opts
  "Default :passes-opts for `analyze`"
  {:collect/what                    #{:constants :callsites}
   :collect/where                   #{:deftype :reify :fn}
   :collect/top-level?              false
   :collect-closed-overs/where      #{:deftype :reify :fn :loop :try}
   :collect-closed-overs/top-level? false})

(def default-passes
  "Set of passes that will be run by default on the AST by #'run-passes"
  #{#'warn-on-reflection
    #'warn-earmuff
    #'uniquify-locals
    #'source-info
    #'elide-meta
    #'constant-lift
    #'trim
    #'box
    #'analyze-host-expr
    #'validate-loop-locals
    #'validate
    #'infer-tag
    #'classify-invoke})

(def scheduled-default-passes
  (schedule default-passes))

(defn ^:dynamic run-passes
  "Function that will be invoked on the AST tree immediately after it has been constructed,
   by default runs the passes declared in #'default-passes, should be rebound if a different
   set of passes is required.

   Use #'analyzer.passes/schedule to get a function from a set of passes that
   run-passes can be bound to."
  [ast]
  (scheduled-default-passes ast))

(defn analyze
  "Analyzes a clojure form using tools.analyzer augmented with the JVM specific special ops
   and returns its AST, after running #'run-passes on it.

   If no configuration option is provides, analyze will setup tools.analyzer using the extension
   points declared in this namespace.

   If provided, opts should be a map of options to analyze, currently the only valid
   options are :bindings and :passes-opts (if not provided, :passes-opts defaults to the
   value of `default-passes-opts`).
   If provided, :bindings should be a map of Var->value pairs that will be merged into the
   default bindings for tools.analyzer, useful to provide custom extension points.
   If provided, :passes-opts should be a map of pass-name-kw->pass-config-map pairs that
   can be used to configure the behaviour of each pass.

   E.g.
   (analyze form env {:bindings  {#'ana/macroexpand-1 my-mexpand-1}})"
  ([form] (analyze form (empty-env) {}))
  ([form env] (analyze form env {}))
  ([form env opts]
   (with-bindings (merge {Compiler/LOADER     (RT/makeClassLoader)
                          #'ana/macroexpand-1 macroexpand-1
                          #'ana/create-var    create-var
                          #'ana/parse         parse
                          #'ana/var?          var?
                          #'elides            (merge {:fn    #{:line :column :end-line :end-column :file :source}
                                                      :reify #{:line :column :end-line :end-column :file :source}}
                                                     elides)
                          #'*ns*              (the-ns (:ns env))}
                         (:bindings opts))
     (env/ensure (global-env)
                 (doto (env/with-env (u/mmerge (env/deref-env)
                                             {:passes-opts (get opts :passes-opts default-passes-opts)})
                         (run-passes (-analyze form env)))
                   (do (update-ns-map!)))))))

(defn analyze+eval
  "Like analyze but evals the form after the analysis and attaches the
   returned value in the :result field of the AST node.

   If evaluating the form will cause an exception to be thrown, the exception
   will be caught and wrapped in an ExceptionThrown object, containing the
   exception in the `e` field and the AST in the `ast` field.

   The ExceptionThrown object is then passed to `handle-evaluation-exception`,
   which by defaults throws the original exception, but can be used to provide
   a replacement return value for the evaluation of the AST.

   Unrolls `do` forms to handle the Gilardi scenario.

   Useful when analyzing whole files/namespaces."
  ([form] (analyze+eval form (empty-env) {}))
  ([form env] (analyze+eval form env {}))
  ([form env {:keys [handle-evaluation-exception]
              :or {handle-evaluation-exception throw!}
              :as opts}]
   (env/ensure (global-env)
               (update-ns-map!)
               (let [env (merge env (u/-source-info form env))
                     [mform raw-forms] (with-bindings {Compiler/LOADER     (RT/makeClassLoader)
                                                       #'*ns*              (the-ns (:ns env))
                                                       #'ana/macroexpand-1 (get-in opts [:bindings #'ana/macroexpand-1] macroexpand-1)}
                                         (loop [form form raw-forms []]
                                           (let [mform (ana/macroexpand-1 form env)]
                                             (if (= mform form)
                                               [mform (seq raw-forms)]
                                               (recur mform (conj raw-forms
                                                                  (if-let [[op & r] (and (seq? form) form)]
                                                                    (if (or (u/macro? op  env)
                                                                            (u/inline? op r env))
                                                                      (vary-meta form assoc ::ana/resolved-op (u/resolve-sym op env))
                                                                      form)
                                                                    form)))))))]
                 (if (and (seq? mform) (= 'do (first mform)) (next mform))
           ;; handle the Gilardi scenario
                   (let [[statements ret] (u/butlast+last (rest mform))
                         statements-expr (mapv (fn [s] (analyze+eval s (-> env
                                                                           (u/ctx :ctx/statement)
                                                                           (assoc :ns (ns-name *ns*)))
                                                                     opts))
                                               statements)
                         ret-expr (analyze+eval ret (assoc env :ns (ns-name *ns*)) opts)]
                     {:op         :do
                      :top-level  true
                      :form       mform
                      :statements statements-expr
                      :ret        ret-expr
                      :children   [:statements :ret]
                      :env        env
                      :result     (:result ret-expr)
                      :raw-forms  raw-forms})
                   (let [a (analyze mform env opts)
                         frm (emit-form a)
                         result (try (eval frm) ;; eval the emitted form rather than directly the form to avoid double macroexpansion
                                     (catch Exception e
                                       (handle-evaluation-exception (ExceptionThrown. e a))))]
                     (merge a {:result    result
                               :raw-forms raw-forms})))))))
