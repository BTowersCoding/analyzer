(ns sublist)

(defn classify [list1 list2] ;; <- arglist goes here
      ;; your code goes here
  (if (= list1 list2) 
    :equal
    (let [sub-lists-1 (set (map #(take (count list2) %) (take-while not-empty (iterate next list1))))
          sub-lists-2 (set (map #(take (count list1) %) (take-while not-empty (iterate next list2))))]
      (cond
        (contains? sub-lists-1 list2) :superlist
        (contains? sub-lists-2 list1) :sublist
        :else :unequal))))
