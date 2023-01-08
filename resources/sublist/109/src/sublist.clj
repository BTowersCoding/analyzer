(ns sublist)

(defn head-of-lists-is-equivalent?
  [list1 list2]
  (every? true? (map = list1 list2)))

(defn is-contained?
  [list1 list2]
  (if (>= (count list2) (count list1))
    (if (head-of-lists-is-equivalent? list1 list2)
        true
        (recur list1 (rest list2))))
  )

(defn classify [list1 list2]
  (cond
    (= list1 list2) :equal
    (is-contained? list1 list2) :sublist
    (is-contained? list2 list1) :superlist
    :else :unequal))