(ns sublist)

(defn sublist? "Determine if list2 contains list1" [list1 list2]
  (cond
    (empty? list1) true
    (> (count list1) (count list2)) false
    :else
    (loop [x1 list1 x2 list2]
      (cond
        (= (count x2) 0) false
        (= (first x1) (first x2))
        (cond ;;The first values match-- now look ahead for a match or keep going
          (< (count x2) (count x1)) false
          (= x1 (take (count x1) x2 )) true
          :else (recur x1 (rest x2)))
        :else (recur x1 (rest x2))))))

(defn classify [list1 list2] ;; <- arglist goes here
  (cond
    (= list1 list2) :equal
    (sublist? list1 list2) :sublist
    (sublist? list2 list1) :superlist
    :default :unequal
    )
)
