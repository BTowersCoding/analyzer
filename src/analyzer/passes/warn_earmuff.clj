(ns analyzer.passes.warn-earmuff
  (:require [analyzer.utils :refer [dynamic?]]))

(defn warn-earmuff
  "Prints a warning to *err* if the AST node is a :def node and the
   var name contains earmuffs but the var is not marked dynamic"
  {:pass-info {:walk :pre :depends #{}}}
  [ast]
  (let [name (str (:name ast))]
    (when (and (= :def (:op ast))
               (> (count name) 2)  ;; Allow * and ** as non-dynamic names
               (.startsWith name "*")
               (.endsWith name "*")
               (not (dynamic? (:var ast) (:val (:meta ast)))))
      (binding [*out* *err*]
        (println "Warning:" name "not declared dynamic and thus is not dynamically rebindable,"
                 "but its name suggests otherwise."
                 "Please either indicate ^:dynamic" name "or change the name"))))
  ast)
