(ns analyzer.main
  (:require [rewrite-clj.zip :as z]
            [clojure.java.io :as io]))

(defn normalize
  "Takes a Java.io.File containing Clojure code
   and outputs a string representing a normalized, 
   fully macroexpanded version of itself."
  [f]
  (-> (str f)
      z/of-file
      z/up
      z/sexpr))

(normalize
 (io/file "resources/sublist/0/src/sublist.clj"))