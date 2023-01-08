(ns sublist)

(defn consecutive? [list1 list2]
   (cond
     (> (count list1) (count list2)) false
     (every? identity (map = list1 (take (count list1) list2))) true
     :else (consecutive? list1 (rest list2)))
  )
  
(defn classify [list1 list2] ;; <- arglist goes here
      (let  [c1 (consecutive? list1 list2) c2 (consecutive? list2 list1)]
        (cond 
          (and c1 c2) :equal
          (true? c1)  :sublist
          (true? c2)  :superlist
          :else :unequal))
)
