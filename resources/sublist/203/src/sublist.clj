(ns sublist)

(defn equal? [list1 list2]
  (= list1 list2))

(defn compare [list1 list2]
  (let [size (count list1)]
    (->> list2
      (partition size 1)
      (some #(= list1 %)))))

(defn sublist? [list1 list2]
  (compare list1 list2))

(defn superlist? [list1 list2]
  (compare list2 list1))

(defn classify [list1 list2]
  (cond
    (equal? list1 list2) :equal
    (sublist? list1 list2) :sublist
    (superlist? list1 list2) :superlist
    :else :unequal))
