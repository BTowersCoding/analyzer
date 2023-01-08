(ns sublist)

(defn classify [list1 list2]
  (cond
    (= list1 list2)                :equal
    (= list1
       (take (count list1) list2)) :sublist
    (< (count list1) 
       (count list2))              (case (classify list1 (rest list2))
                                     :equal   :sublist
                                     :sublist :sublist
                                     :unequal)
    (= (take (count list2) list1)
       list2)                      :superlist
    (> (count list1)
       (count list2))              (case (classify (rest list1) list2)
                                     :equal     :superlist
                                     :superlist :superlist
                                     :unequal)
    true              :unequal)
)
