(ns sublist)

(defn classify [list1 list2] ;; <- arglist goes here
  (let [match? (fn [xs ys] 
                 (->>
                   (partition (count xs) 1 ys)
                   (some #(= xs %)) ;; why does some return nil instead of false?
                   (some?)
                 ))]
        (cond 
          (= list1 list2) :equal
          (match? list1 list2) :sublist
          (match? list2 list1) :superlist
          :else :unequal
        )
  )    
)
