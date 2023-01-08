(ns sublist)

(defn bool-list [l1 l2]
  (map (fn [x] (contains? l1 x)) l2))

(defn classify [list1 list2]
  (cond
    (> (count list1) (count list2))
    (if (every? true? (bool-list list1 list2))
      :superlist
      :unequal)
    (< (count list1) (count list2))
    (if (every? true? (bool-list list2 list1))
      :sublist
      :unequal)
    (= list1 list2)
    :equal
    (not (= list1 list2))
    :unequal))
