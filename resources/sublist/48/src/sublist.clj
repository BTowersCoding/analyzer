(ns sublist)

(defn classify [list1 list2]
  (cond
    (= list1 list2) :equal
    (= list1 (take (count list1) list2)) :sublist
    (= list2 (take (count list2) list1)) :superlist
    (< (count list1) (count list2)) (let [classification (classify list1 (rest list2))]
                                      (if (= classification :equal)
                                        :sublist
                                        classification))
    (> (count list1) (count list2)) (let [classification (classify (rest list1) list2)]
                                      (if (= classification :equal)
                                        :superlist
                                        classification))
    :else :unequal))
