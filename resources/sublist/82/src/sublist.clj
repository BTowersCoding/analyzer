(ns sublist)

(defn sublist? [list1 list2]
  (some #{list1} (partition (count list1) 1 list2)))

(defn superlist? [list1 list2]
  (some #{list2} (partition (count list2) 1 list1)))

(defn equal? [list1 list2]
  (= list1 list2))

(defn classify [list1 list2]
  (cond 
    (equal? list1 list2) :equal
    (sublist? list1 list2) :sublist
    (superlist? list1 list2) :superlist
    :else :unequal))
