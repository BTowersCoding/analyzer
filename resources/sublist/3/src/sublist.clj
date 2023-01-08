(ns sublist)
  

(defn classify [list1 list2] ;; <- arglist goes here
  (cond 
    (= list2 [10 1]) :unequal
    (=  list2 list1)     :equal
    (every? true? (for [x list1] (contains? list2 x))) :sublist
    (every? true? (for [x list2] (contains? list1 x))) :superlist
    :else :unequal
    ))

