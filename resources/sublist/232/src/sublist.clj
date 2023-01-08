(ns sublist)

(defn classify [list1 list2] ;; <- arglist goes here
  ;; your code goes here
  (cond
    (= list1 list2) :equal
    (= list1 []) :sublist
    (= list2 []) :superlist
    (every? (fn [x] (.contains list2 x)) list1) :sublist
    (every? (fn [x] (.contains list1 x)) list2) :superlist
    :default :unequal
    )
  )
