(ns sublist 
  (:require clojure.set))

(defn classify [list1 list2]
  (if (= list1 list2) :equal
      (if (clojure.set/subset? list1 list2) :sublist
          (if (clojure.set/subset? list2 list1) :superlist :unequal))))