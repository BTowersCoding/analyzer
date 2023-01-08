(ns sublist)

(defn remove-first-n
  [l n]
  (loop [rem l cnt 0]
    (if (= cnt n)
      rem
      (recur (rest rem) (inc cnt)))))

(defn is-sublist
  [a b]
  (loop [remA a remB b cnt 0]
    (if (> (count remA) (count remB))
      false
      (if (empty? remA)
        true
        (if (= (first remA) (first remB))
          (recur (rest remA) (rest remB) cnt)
          (recur a (remove-first-n b (inc cnt)) (inc cnt)))))))

(defn classify [list1 list2] ;; <- arglist goes here
     ;; your code goes here
  (if (= (count list1) (count list2))
    (if (= list1 list2)
      :equal
      :unequal)
    (if (> (count list1) (count list2))
      (if (is-sublist list2 list1)
        :superlist
        :unequal)
      (if (is-sublist list1 list2)
        :sublist
        :unequal))))
