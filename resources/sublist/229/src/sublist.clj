(ns sublist)

(defn is-sublist
  [list1 list2]

  (if (>= (->>
           (partition (count list1) 1 list2)
           (filter #(= list1 %))
           (count)) 1) true false))

(defn classify [list1 list2]
  (cond
    (= list1 list2) :equal
    (is-sublist list1 list2) :sublist
    (is-sublist list2 list1) :superlist
    :else :unequal))

