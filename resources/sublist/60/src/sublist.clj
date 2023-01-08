(ns sublist)

;; if list1 is sublist of list2
(defn sbl? [list1 list2]
  (cond
    (empty? list1) true
    (or (empty? list2) (> (count list1) (count list2))) false
    (= (take (count list1) list2) list1) true
    :else (sbl? list1 (drop 1 list2)))
)

(defn classify [list1 list2] 
  ;; <- arglist goes here
  ;; your code goes here
  (let [a (sbl? list1 list2) b (sbl? list2 list1)]
    (cond 
      (and a b) (keyword "equal")
      (= true a) (keyword "sublist")
      (= true b) (keyword "superlist")
      :else (keyword "unequal")
    )
  )
)