(ns sublist)


(defn sublist? [coll1 coll2]
  (some #{coll1} (partition (count coll1) 1 coll2)))

(defn classify [list1 list2]
  (cond
    (= list1 list2) :equal
    (sublist? list1 list2) :sublist
    (sublist? list2 list1) :superlist
    (not= list1 list2) :unequal))

