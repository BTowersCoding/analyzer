(ns sublist)

(defn sublist?
  [list1 list2]
  (some #{list1} (partition (count list1) 1 list2)))

(defn classify [list1 list2] ;; <- arglist goes here
      ;; your code goes here
  (if (= list1 list2)
    :equal
    (if (sublist? list1 list2) ; else 1
      :sublist
      (if (sublist? list2 list1) ; else 2
        :superlist
        :unequal ; else 3
        ))))
