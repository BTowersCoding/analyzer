(ns sublist)

(defn sublist? [list1 list2]
  (some #(= list2 %) (partition (count list2) 1 list1))
  )

(defn classify [list1 list2] ;; <- arglist goes here
  (cond
    (= list1 list2) :equal
    (= [] list1) :sublist
    (= [] list2) :superlist
    (sublist? list1 list2) :superlist
    (sublist? list2 list1) :sublist
    :else :unequal)
)
