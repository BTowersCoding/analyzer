(ns sublist)

(defn classify [list1 list2]
  (cond (= list1 list2) :equal
        (clojure.set/superset? list1 list2) :superlist
        (clojure.set/subset? list1 list2) :sublist
        (not (= list1 list2)) :unequal)) 
