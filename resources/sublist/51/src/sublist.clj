(ns sublist)

(defn is-sub [right left]
  (if (= (take (count left) right) left)
    true
    (if (> (count right) (count left))
      (is-sub (rest right) left)
      false)))

(defn classify [list1 list2]
  (if (= (count list1) (count list2))
    (if (= list1 list2) :equal :unequal)
    (if (> (count list1) (count list2))
      (if (is-sub list1 list2) :superlist :unequal)
      (if (is-sub list2 list1) :sublist :unequal))))
