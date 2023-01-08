(ns sublist)

(defn sublist? [sublist superlist] 
   (some #(= sublist %) (partition (count sublist) 1 superlist))
)

(defn classify [list1 list2] ;; <- arglist goes here
  (let [sub (sublist? list1 list2)
        sup (sublist? list2 list1)]

    (cond
      (or 
       (and sub sup)
       (and (empty? list1) (empty? list2))
      ) :equal
      (true? sub) :sublist
      (true? sup) :superlist
      :else :unequal  
      )
    
    )
)
