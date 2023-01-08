(ns sublist)

(defn classify
  [list1 list2]
  (let [list1-partitions (partition (count list2) 1 list1)
        list2-partitions (partition (count list1) 1 list2)]
    (cond
      (= list1 list2)                      :equal
      (some #(= list1 %) list2-partitions) :sublist
      (some #(= list2 %) list1-partitions) :superlist
      :else                                :unequal)))
