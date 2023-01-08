(ns sublist)

(defn classify
  "Determines the relationship between two lists.
  If they are the same, returns :equal.
  If each list doesn't contain the other, returns :unequal.
  If list1 contains list2, but list2 doesn't contain list1, returns :superlist.
  If list2 contains list 1, but list1 doesn't contain list2, returns :sublist."
  [list1 list2]
  (let [list1-parts (partition (count list2) 1 list1)
        list2-parts (partition (count list1) 1 list2)]
    (cond
      (= list1-parts list2-parts) :equal
      (some #(= list2 %) list1-parts) :superlist
      (some #(= list1 %) list2-parts) :sublist
      :default :unequal)))
