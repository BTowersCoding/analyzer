(ns sublist)

(defn sublist? [list1 list2]
  (cond
    (> (count list1) (count list2)) false
    (= list1 []) true
    (some #(= % list1) (partition (count list1) 1 list2)) true
    :else false
    )
  )

(defn classify [list1 list2] ;; <- arglist goes here
      (cond
        (= list1 list2) :equal
        (sublist? list1 list2) :sublist
        (sublist? list2 list1) :superlist
        :else :unequal
        
        
        )
)
