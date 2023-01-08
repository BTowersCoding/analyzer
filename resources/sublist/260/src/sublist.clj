(ns sublist)

(defn sublist? [list sublist]
  (if (empty? sublist) true
    (some #(= sublist %) (partition (count sublist) 1 list)
      )))

(defn classify [list1 list2] ;; <- arglist goes here
      ;; your code goes here
  (let [count-comparision (compare (count list1) (count list2))]
    (case count-comparision
      1 (if (sublist? list1 list2) :superlist :unequal)
      0 (if (= list1 list2) :equal :unequal)
      (if (sublist? list2 list1) :sublist :unequal)
)))
