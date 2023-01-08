(ns sublist)

(defn sublist? [a b]
  (some #{a} (partition (count a) 1 b)))

(defn classify [list1 list2]
  (if (= list1 list2)
    :equal
    (if (sublist? list1 list2)
      :sublist
      (if (sublist? list2 list1)
        :superlist
        :unequal))))
