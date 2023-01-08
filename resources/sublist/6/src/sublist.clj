(ns sublist)

(defn- compare-collection [list1 list2]
  (every? identity (map = list1 list2)))

(defn- sublist? [list1 list2 result]
  (if (empty? list1)
    :unequal
    (if (compare-collection list1 list2)
      result
      (sublist? (rest list1) list2 result))))

(defn classify [list1 list2]
  (let [len1 (count list1)
        len2 (count list2)]
    (cond
      (= len1 len2) (if (compare-collection list1 list2) :equal :unequal)
      (> len1 len2) (sublist? list1 list2 :superlist)
      :else (sublist? list2 list1 :sublist))))

(def lst1 [0 1 2 3 4 5])
(def lst2 [2 3])
(classify lst1 lst2)
(classify lst2 lst1)
