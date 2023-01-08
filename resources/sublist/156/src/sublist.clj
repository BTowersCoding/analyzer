(ns sublist)

(defn sublist? [list1 list2]
  (let [c1 (count list1)
        c2 (count list2)]
  (if (<= c1 c2)
    (or (= list1 (take c1 list2))
        (sublist? list1 (rest list2)))
    false
  )))


(defn classify [list1 list2]
  (cond
    (= list1 list2) :equal
    (sublist? list1 list2) :sublist
    (sublist? list2 list1) :superlist
    :else :unequal)
)
