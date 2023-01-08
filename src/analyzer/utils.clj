(ns analyzer.utils)

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
