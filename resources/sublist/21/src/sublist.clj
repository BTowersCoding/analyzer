(ns sublist)

(defn sublist? [list1 list2]
  (if (seq list2)
    (if (= list1 (take (count list1) list2))
      true
      (recur list1 (rest list2)))
    false))

(defn classify [list1 list2]
  (cond
    (= list1 list2) :equal
    (sublist? list1 list2) :sublist
    (sublist? list2 list1) :superlist
    :else :unequal))
