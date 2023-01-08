(ns sublist)

(defn classify [list1 list2]
  (let [length1 (count list1)
        length2 (count list2)]
    (cond
      (= 0 length1 length2) :equal
      (zero? length1) :sublist
      (zero? length2) :superlist
      (= length1 length2)
      (if (= list1 list2) :equal :unequal)
      (< length1 length2)
      (if (contains? (set (partition length1 1 list2)) list1)
        :sublist
        :unequal)
      :else (if (contains? (set (partition length2 1 list1)) list2)
              :superlist
              :unequal))))
