(ns sublist)

(defn is-sublist? [list1 list2]
  (def n1 (count list1))
  (def n2 (count list2))
  (loop [start 0]
    (when (< start n2)
      (if
       (= list1 (take n1 (drop start list2))) true
       (recur (inc start))))))

(defn classify [list1 list2] ;; <- arglist goes here
  (cond
    (= list1 list2) :equal
    (is-sublist? list1 list2) :sublist
    (is-sublist? list2 list1) :superlist
    :else :unequal))
