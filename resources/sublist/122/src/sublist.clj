(ns sublist)

(defn sublist? [list1 list2]
  (->> (flatten list2)
       (partition (count list1) 1)
       (map (partial = list1))
       (some true?)))

(defn superlist? [list1 list2]
  (sublist? list2 list1))

(defn classify [list1 list2]
  (cond
    (= list1 list2) :equal
    (sublist? list1 list2) :sublist
    (superlist? list1 list2) :superlist
    :else :unequal))
