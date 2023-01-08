(ns sublist)

(defn subseq? [a b]
  (some #{a} (partition (count a) 1 b)))

(defn classify [list1 list2] ;; <- arglist goes here
      ;; your code goes here
  (if (= (count list1) (count list2))
    (if (= list1 list2)
    :equal
    :unequal)
    (if-let [gth-first (> (count list1) (count list2))]
      (if (subseq? list2 list1)
        :superlist
        :unequal)
      (if (subseq? list1 list2)
        :sublist
        :unequal)))
)
