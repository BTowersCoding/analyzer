(ns sublist)

(defn sublist? [list1 list2]
  (cond
    (= list1 list2) true
    (and (> (count list1) 0) (= (count list2) 0)) true
    (= (count list1) 0) false
    (sublist? (pop list1) list2) true
    (sublist? (vec (rest list1)) list2) true
    :else false
    )
  )

(defn classify [list1 list2] ;; <- arglist goes here
      (cond
        (= list1 list2) :equal
        (sublist? list1 list2) :superlist
        (sublist? list2 list1) :sublist
        :else :unequal)
)
