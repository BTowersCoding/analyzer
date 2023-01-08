(ns sublist)

(defn contains [list1 list2]
  (if (empty? list1) false (recur (contains list1) list2)))

(defn classify [list1 list2]
  (cond (= list1 list2) :equal
        (contains list1 list2) :sublist
        (contains list2 list1) :superlist
        :else :unequal))

