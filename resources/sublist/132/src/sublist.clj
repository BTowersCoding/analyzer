(ns sublist)

(defn classify [list1 list2]
  (cond (= list1 list2) :equal
    (or (= list1 [])
            (some #{list1} (partition (count list1) 1 list2))) :sublist
    (or (= list2 [])
        (some #{list2} (partition (count list2) 1 list1))) :superlist
    (= list1 list2) :equal
    :else :unequal))
