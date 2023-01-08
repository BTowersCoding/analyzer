(ns sublist)

(defn sublist? [list1 list2]
  (if (> (count list1) (count list2))
    false
    (if (= list1 (take (count list1) list2))
      true
      (sublist? list1 (rest list2)))))

(defn classify [list1 list2]
  (if (= list1 list2)
    :equal
    (if (and (< (count list1) (count list2)) (sublist? list1 list2))
      :sublist
      (if (sublist? list2 list1) :superlist :unequal))))
