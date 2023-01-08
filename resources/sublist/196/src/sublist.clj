(ns sublist)

(defn classify [list1 list2]
  (def len-list1 (count list1))
  (def len-list2 (count list2))
  (cond
    (= len-list1 len-list2) (if (every? #(= % true)(mapv #(= %1 %2) list1 list2)) (keyword "equal")   (keyword "unequal") )
    (< len-list1 len-list2) ( if (some #{list1} (partition len-list1 1 list2)) (keyword "sublist") (keyword "unequal"))
    (< len-list2 len-list1) ( if (some #{list2} (partition len-list2 1 list1)) (keyword "superlist") (keyword "unequal"))
    )
)
