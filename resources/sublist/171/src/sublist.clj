(ns sublist)

(defn sublist-of? [list1 list2]
  (cond (= [] list1) true
        (= [] list2) false
        (= list1 (take (count list1) list2)) true
        :else (recur list1 (drop 1 list2))))

(defn classify [list1 list2]
  (cond (= list1 list2) :equal
        (sublist-of? list1 list2) :sublist
        (sublist-of? list2 list1) :superlist
        :else :unequal))

