(ns sublist)

(defn sublists [n a]
  (set (partition n 1 a)))

(defn classify [list1 list2] ;; <- arglist goes here
  (cond
    (= list1 list2) :equal
    (contains? (sublists (count list1) list2) list1) :sublist
    (contains? (sublists (count list2) list1) list2) :superlist
    :else :unequal))
