(ns sublist)

(defn classify [list1 list2]
  (cond
    (= list1 list2) :equal
    (= (count list1) (count list2)) :unequal
    (> (count list1) (count list2)) (if (superlist? list1 list2) :superlist :unequal)
    :otherwise (if (superlist? list2 list1) :sublist :unequal)))

(defn superlist? [big small]
  (some #(= small %) (partition (count small) 1 big)))
