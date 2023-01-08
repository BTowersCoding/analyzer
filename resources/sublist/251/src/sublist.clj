(ns sublist)

(defn is-sublist [list1 list2]
  (defn is-sublist-iter? [list1 list2]
    (let [x (first list1) y (first list2)]
      (cond
        (nil? x) true
        (= x y) (is-sublist-iter? (rest list1) (rest list2))
        :else false)))
  
  (defn iterate [curr-list2]
    (cond
      (empty? curr-list2) false
      (is-sublist-iter? list1 curr-list2) true
      :else (iterate (rest curr-list2))))

  (if (empty? list1) true (iterate list2)))


(defn classify [list1 list2] ;; <- arglist goes here
      ;; your code goes here
  (cond
    (is-sublist list1 list2) (if (= (count list1) (count list2)) :equal :sublist)
    (is-sublist list2 list1) (if (= (count list1) (count list2)) :equal :superlist)
    :else :unequal)
)
