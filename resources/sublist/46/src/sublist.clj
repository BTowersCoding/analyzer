(ns sublist)

(defn classify [list1 list2]
  (let [list1-size (count list1)
        list2-size (count list2)
        [min-list max-list result] (if (< list1-size list2-size) [list1 list2 :sublist] [list2 list1 :superlist])
        min-list-size (count min-list)
        max-list-size (count max-list)]
    (cond
      (= list1-size list2-size)
        (if (= list1 list2) :equal :unequal) 
      (= min-list-size 0)
        result
      :otherwise
        (if (empty? (filter #(= % min-list) (partition min-list-size 1 max-list)))
          :unequal
          result))))

