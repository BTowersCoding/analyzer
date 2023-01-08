(ns sublist)

(defn compare [list1 list2]
  (cond 
    (= list1 ()) true
    (not= (first list1) (first list2)) false
    :else (compare (rest list1) (rest list2))))

(defn sublist? [list1 list2]
  (cond 
    (= list1 ()) true
    (= list2 ()) false
    (= (first list1) (first list2)) 
    (if (compare list1 list2) 
      true 
      (sublist? list1 (rest list2)))
    :else (sublist? list1 (rest list2))
  ))

(defn equal-list? [list1 list2]
  (cond 
    (and (= list1 '()) (= list2 '())) true
    (not= (first list1) (first list2)) false
    :else (equal-list? (rest list1) (rest list2))))

(defn classify [list1 list2]
  (cond
    (equal-list? list1 list2) :equal
    (sublist? list1 list2) :sublist
    (sublist? list2 list1) :superlist
    :else :unequal))

