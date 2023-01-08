(ns sublist)

(defn classify [list1 list2]
  (letfn [(sublist? [sublist superlist]
            (some #{sublist} (partition (count sublist) 1 superlist)))]
    (cond
      (= list1 list2) :equal
      (sublist? list1 list2) :sublist
      (sublist? list2 list1) :superlist
      :else :unequal
      ))
  )
