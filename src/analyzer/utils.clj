(ns analyzer.utils
  (:require [analyzer.env :as env])
  (:import (clojure.lang IRecord IType IObj
                         IReference Var)))

(defn obj?
  "Returns true if x implements IObj"
  [x]
  (instance? IObj x))

(defn ctx
  "Returns a copy of the passed environment with :context set to ctx"
  [env ctx]
  (assoc env :context ctx))

(defn dissoc-env
  "Dissocs :env from the ast"
  [ast]
  (dissoc ast :env))

(defmulti ^Class maybe-class
  "Takes a Symbol, String or Class and tires to resolve to a matching Class"
  class)

(defn resolve-ns
  "Resolves the ns mapped by the given sym in the global env"
  [ns-sym {:keys [ns]}]
  (when ns-sym
    (let [namespaces (:namespaces (env/deref-env))]
      (or (get-in namespaces [ns :aliases ns-sym])
          (:ns (namespaces ns-sym))))))

(defn resolve-sym
  "Resolves the value mapped by the given sym in the global env"
  [sym {:keys [ns] :as env}]
  (when (symbol? sym)
    (let [sym-ns (when-let [ns (namespace sym)]
                   (symbol ns))
          full-ns (resolve-ns sym-ns env)]
      (when (or (not sym-ns) full-ns)
        (let [name (if sym-ns (-> sym name symbol) sym)]
          (-> (env/deref-env) :namespaces (get (or full-ns ns)) :mappings (get name)))))))

(defn dynamic?
  "Returns true if the var is dynamic"
  ([var] (dynamic? var nil))
  ([var m]
   (or (:dynamic (or m (meta var)))
       (when (var? var) ;; workaround needed since Clojure doesn't always propagate :dynamic
         (.isDynamic ^Var var)))))

(defn merge'
  "Like merge, but uses transients"
  [m & mms]
  (persistent! (reduce conj! (transient (or m {})) mms)))

(def mmerge
  "Same as (fn [m1 m2] (merge-with merge m2 m1))"
  #(merge-with merge' %2 %1))

(defn select-keys'
  "Like clojure.core/select-keys, but uses transients and doesn't preserve meta"
  [map keyseq]
  (loop [ret (transient {}) keys (seq keyseq)]
    (if keys
      (let [entry (find map (first keys))]
        (recur (if entry
                 (conj! ret entry)
                 ret)
               (next keys)))
      (persistent! ret))))

(defn source-info
  "Returns the available source-info keys from a map"
  [m]
  (when (:line m)
    (select-keys' m #{:file :line :column :end-line :end-column :source-span})))

(defn -source-info
  "Returns the source-info of x"
  [x env]
  (merge' (source-info env)
          (source-info (meta x))
          (when-let [file (and (not= *file* "NO_SOURCE_FILE")
                               *file*)]
            {:file file})))

(defn butlast+last
  "Returns same value as (juxt butlast last), but slightly more
   efficient since it only traverses the input sequence s once, not
   twice."
  [s]
  (loop [butlast (transient [])
         s s]
    (if-let [xs (next s)]
      (recur (conj! butlast (first s)) xs)
      [(seq (persistent! butlast)) (first s)])))