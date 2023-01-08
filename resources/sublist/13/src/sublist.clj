(ns sublist)

(defn sublist? [l1 l2]
  (some #(= (apply list l1) %) (partition (count l1) 1 l2)))

(defn classify [list1 list2] 
  (cond
    (= list1 list2) :equal
    (empty? list1) :sublist
    (empty? list2) :superlist
    (sublist? list1 list2) :sublist
    (sublist? list2 list1) :superlist 
    (not= list1 list2) :unequal))


