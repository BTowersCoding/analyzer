(ns sublist)

(defn sublist [a b]
  (some #{a true} (partition (count a) 1 b))
  )

(defn classify [list1 list2] ;; <- arglist goes here
      ;; your code goes here
         (cond
           (= list1 list2) :equal
           (sublist list2 list1) :superlist
           (sublist list1 list2) :sublist
           :default :unequal) 
         )

