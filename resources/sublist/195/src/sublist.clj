(ns sublist)

(defn classify
  [list1 list2]
  (cond
    (and
      (not-empty list2)
      (clojure.set/subset? list1 list2)) :sublist
    (and
      (not-empty list1)
      (clojure.set/superset? list1 list2)) :superlist
    (= list1 list2) :equal
    true :unequal))
