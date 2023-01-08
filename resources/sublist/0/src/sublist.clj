(ns sublist)
(defn subseq? [a b]
    (some #{a} (partition (count a) 1 b)))
(defn classify [list1 list2];; <- arglist goes here
      ;; your code goes here
  (if (= list1 list2)
    :equal
    (if (= (subseq? list1 list2) list1)
      :sublist
      (if (= (subseq? list2 list1) list2)
        :superlist
        :unequal
      )
    )
  )
)
