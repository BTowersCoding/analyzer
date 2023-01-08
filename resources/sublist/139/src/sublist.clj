(ns sublist)

(defn classify [list1 list2] ;; <- arglist goes here

  (cond
    (= list1 list2) :equal
    (or (= (count list1) 0) (some #(= % (seq list1)) (partition (count list1) 1 list2))) :sublist
    (or (= (count list2) 0) (some #(= % (seq list2)) (partition (count list2) 1 list1))) :superlist
    :else :unequal))

