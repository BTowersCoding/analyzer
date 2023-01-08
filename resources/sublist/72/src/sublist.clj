(ns sublist)

(defn check-sublist [list1 list2] ;; note list1 is smaller than list2
  (println "List1: " list1 " List2: " list2)
  (if (< (count list1) (count list2))
    (or (check-sublist list1 (vec (rest list2))) (check-sublist list1 (pop list2)))
    (= list1 list2)
    )
  )

(defn classify [list1 list2] ;; <- arglist goes here
      (if (= (count list1) (count list2))
        (if (= list1 list2) :equal :unequal)
        (if (< (count list1) (count list2)) 
          (if (check-sublist list1 list2) :sublist :unequal) 
          (if (check-sublist list2 list1) :superlist :unequal)))
)
