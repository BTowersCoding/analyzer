(ns sublist)

(defn sublist? [listA listB]
  (some #{listA} (partition (count listA) 1 listB)))

(defn classify [list1 list2]
  (cond
    (= list1 list2) :equal
    (sublist? list1 list2) :sublist
    (sublist? list2 list1) :superlist
    :default :unequal))
