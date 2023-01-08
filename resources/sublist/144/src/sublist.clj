(ns sublist)

(defn second-is-subset-of-first [list1 list2]
  (let [length1 (count list1)
        length2 (count list2)
        number-possible (+ 1 (- length1 length2))
        possibles (for [start (range number-possible)] (subvec list1 start (+ start length2)))
        matches (for [x possibles] (= x list2))]
    (some true? matches)))

(defn classify [list1 list2]
  (cond
    (= list1 list2) :equal
    (= 0 (count list1)) :sublist
    (= 0 (count list2)) :superlist
    (second-is-subset-of-first list1 list2) :superlist
    (second-is-subset-of-first list2 list1) :sublist
    :else :unequal
    )
  )
