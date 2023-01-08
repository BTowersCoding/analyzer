(ns sublist)

(defn check-for-sublist
  [list1 list2 flip]
  (if (and (= (count list1) 0) (= (count list2) 0))
      :equal
       (if (.contains list2 (first list1))
         (if (= (->> list2
                       (drop (.indexOf list2 (first list1)))
                       (take (count list1))) list1)
           (if (= (count list1) (count list2))
             :equal
             (if (= flip true)
               :superlist
               :sublist))
           :unequal)
         :unequal))
  )


(defn compare-lists 
  [list1 list2 flip]
  (loop 
   [truncated-list list2]
    (if (or (> (count list1) (count truncated-list)) 
               (not (some #(= (first list1) %) truncated-list)))
      :unequal
      (let [result (check-for-sublist list1 truncated-list flip)]
        (if (not (= result :unequal))
          result
          (recur (drop (inc (.indexOf truncated-list (first list1))) truncated-list))))
      )
  )
)
    


(defn classify [list1 list2] ;; <- arglist goes here
      ;; your code goes here
  (if (and (= (count list1) 0) (= (count list2) 0))
    :equal
    (if (and (= (count list1) 0) (> (count list2) 1))
      :sublist
      (if (and (= (count list2) 0) (> (count list1) 1))
        :superlist
        (if (or (< (count list1) (count list2)) (= (count list1) (count list2)))
          (compare-lists list1 list2 false)
          (compare-lists list2 list1 true))))))
  
