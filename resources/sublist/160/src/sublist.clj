(ns sublist)

(defn includes? [list1 list2]
  (->> (partition (count list1) 1 list2)
       (some #(= list1 %))))

(defn classify [list1 list2]
  (cond
    (= list1 list2) :equal
    (includes? list1 list2) :sublist
    (includes? list2 list1) :superlist
    :else :unequal
    )
  )