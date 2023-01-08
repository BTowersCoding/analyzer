(ns sublist)

;; helpers
(defn sublist? [list1 list2]
  (some #{list1} (partition (count list1) 1 list2)))

(defn classify [list1 list2] ;; <- arglist goes here
  (cond
    (and (empty? list1) (empty? list2))
      :equal
    (and (sublist? list1 list2)
         (sublist? list2 list1))
      :equal
    (sublist? list1 list2)
      :sublist
    (sublist? list2 list1)
      :superlist
    :else
      :unequal))
