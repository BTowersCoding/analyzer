(ns sublist)

(defn prefix [list1 list2] ;; <- arglist goes here
  (if (= list1 '())
    (if (= list2 '()) :equal :sub-prefix)
    (if (= list2 '())
      :super-prefix
      (if (= (first list1) (first list2)) (prefix (rest list1) (rest list2)) :unequal))))

(defn classify [list1 list2]
  (let [c1 (count list1) c2 (count list2)]
    (if (= c1 c2)
      (prefix list1 list2)
      (let [result (prefix list1 list2)]
        (if (< c1 c2)
          (case result
            :sub-prefix :sublist
            :equal :equal
            :unequal (let [rest-result (classify list1 (rest list2))]
                       (if (= rest-result :equal)
                         :sublist
                         rest-result)))
          (case result
            :super-prefix :superlist
            :equal :equal
            :unequal (let [rest-result (classify (rest list1) list2)]
                       (if (= rest-result :equal)
                         :superlist
                         rest-result))))))))
