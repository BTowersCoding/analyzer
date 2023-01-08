(ns sublist)

(defn check-sublist-old [l1 l2]
  "check if all elements of l1 are present in l2"
  (loop [l l1]
    (cond
      (empty? l) true
      (empty? (filter (fn [x] (= x (first l))) l2)) false
      :else (recur (rest l)))))



(take 2 [1 2 3 4])

(check-sublist-old '(1 2 4) '(1 2 3))

(defn check-sublist [l1 l2]
  (if (= l1 l2)
    true
    (loop [l l2]
      (if (empty? l)
        false
        (as-> l lv
          (take (count l1) lv)
          (if (= lv l1)
            true
            (recur (rest l))))))))



(check-sublist '(1 5 6) '(1 2 5 6 3))

(= '(1 5 6) '(1 2 5 6 3))

(defn classify [list1 list2] ;; <- arglist goes here
      ;; your code goes here
  (if (check-sublist list1 list2)
    (if (check-sublist list2 list1)
      :equal
      :sublist)
    (if (check-sublist list2 list1)
      :superlist
      :unequal)))

(classify '(1 2 3) '(1 2))

(classify [1 2 3] [3 2 1])

(classify [1 2 4 5 6] [4 5])

(classify [4 5] [1 2 4 5 6])