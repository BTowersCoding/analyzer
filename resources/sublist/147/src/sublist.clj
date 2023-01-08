(ns sublist)

(defn subset? [list1 list2]
  (and (<= (count list1) (count list2))
       (every? #(contains? list2 %) list1)))

(defn superset? [list1 list2]
  (subset? list2 list1))

(defn classify [list1 list2]
  (cond
    (= list1 list2) :equal
    (subset? list1 list2) :sublist
    (superset? list1 list2) :superlist
    :else :unequal))
