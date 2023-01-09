(ns analyzer.env
  (:refer-clojure :exclude [ensure]))

(def ^:dynamic *env*
  "Global env atom containing a map.
   Required options:
    * :namespaces a map from namespace symbol to namespace map,
      the namespace map contains at least the following keys:
     ** :mappings a map of mappings of the namespace, symbol to var/class
     ** :aliases a map of the aliases of the namespace, symbol to symbol
     ** :ns a symbol representing the namespace"
  nil)

;; if *env* is not bound, bind it to env
(defmacro ensure
  "If *env* is not bound it binds it to env before executing the body"
  [env & body]
  `(if *env*
     (do ~@body)
     (with-env ~env
       ~@body)))

(defn deref-env
  "Returns the value of the current global env if bound, otherwise
   throws an exception."
  []
  (if *env*
    @*env*
    (throw (Exception. "global env not bound"))))