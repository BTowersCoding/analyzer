(ns sublist)

(defn sublist? [list1 list2]
  (some #{list1} (partition (count list1) 1 list2)))

(defn superlist? [list1 list2]
  (sublist? list2 list1))

(defn classify [list1 list2] ;; <- arglist goes here
  (if (= list1 list2)
    :equal
    (if (sublist? list1 list2)
      :sublist
      (if (superlist? list1 list2)
        :superlist
        :unequal)))
)
