(ns analyzer.utils
  (:require [analyzer.env :as env]))

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

(defn merge'
  "Like merge, but uses transients"
  [m & mms]
  (persistent! (reduce conj! (transient (or m {})) mms)))

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
