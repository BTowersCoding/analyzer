(ns sublist)

(defn sublist? [l1 l2]
  (some #{l1} (partition (count l1) 1 l2))  
  )

(defn classify [list1 list2] ;; <- arglist goes here
  (cond 
    (= list1 list2) :equal
    (sublist? list1 list2) :sublist
    (sublist? list2 list1) :superlist
    :else :unequal)
)
