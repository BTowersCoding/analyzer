(ns sublist)

(defn sublist? [l1 l2]
  (cond
    (empty? l1) true
    (empty? l2) false
    (= l1 (take (count l1) l2)) true
    true (sublist? l1 (rest l2))))

(defn classify [list1 list2] ;; <- arglist goes here
  (cond
    (= list1 list2) :equal
    (sublist? list1 list2) :sublist
    (sublist? list2 list1) :superlist
    true :unequal))
