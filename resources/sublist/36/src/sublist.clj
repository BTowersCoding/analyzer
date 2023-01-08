(ns sublist)

(defn sublist? [list1 list2]
  (cond
    (= list1 list2) true
    (= (count list1) (count list2)) false
    (not (= (first list1) (first list2))) (sublist? list1 (rest list2))
    (not (= (last list1) (last list2))) (sublist? list1 (butlast list2))
    :else (or (sublist? list1 (rest list2)) (sublist? list1 (butlast list2)))))

(defn classify [list1 list2]
  (cond
    (> (count list1) (count list2)) (if (sublist? list2 list1) :superlist :unequal)
    (< (count list1) (count list2)) (if (sublist? list1 list2) :sublist :unequal)
    :else (if (= list1 list2) :equal :unequal)))
