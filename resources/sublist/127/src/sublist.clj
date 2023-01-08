(ns sublist)

(defn sub-list?
  "Is list1 a sublist of list2?"
  [list1 list2]
  (some #{list1} (partition (count list1) 1 list2)))

(defn classify [list1 list2]
  (cond (= list1 list2) :equal
        (sub-list? list1 list2) :sublist
        (sub-list? list2 list1) :superlist
        :else :unequal))
