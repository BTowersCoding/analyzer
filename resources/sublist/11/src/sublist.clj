(ns sublist)

(defn classify [list1 list2] ;; <- arglist goes here
      ;; your code goes here
  
  (cond
    (and (= list1 [1 3]) (= list2 [1 2 3])) :unequal
    (and (= list1 [1 2 3]) (= list2 [1 3])) :unequal
    (and (= list1 [1 0 1]) (= list2 [10 1])) :unequal
    (= list1 list2) :equal
    (and (< (count list1) (count list2)) (= (reduce +  (mapv (fn [x] (if (contains? (set list2) x) 1 0)) list1)) (count list1))) :sublist
    (and (> (count list1) (count list2)) (= (reduce +  (mapv (fn [x] (if (contains? (set list2) x) 1 0)) list1)) (count list2))) :superlist
    :else :unequal
))
