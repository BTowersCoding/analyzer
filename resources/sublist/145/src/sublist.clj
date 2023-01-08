(ns sublist)

(defn sublist [l1 l2]
  (some 
   #(= l1 %) 
   (for 
     [x (range (count l2))]
     (take (count l1) (drop x l2)))))

(defn classify [list1 list2] ;; <- arglist goes here
      ;; your code goes here
  (cond
    (= list1 list2) :equal
    (sublist list1 list2) :sublist 
    (sublist list2 list1) :superlist
    :else :unequal
    )
)
