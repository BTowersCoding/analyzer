(ns sublist)

(defn window [list1 list2]
  (cond (= (take (count list2) list1) list2) true
        (< (count list1) (count list2)) false
        :else (window (rest list1) list2)))

(defn superlist [list1 list2]
  (if (window list1 list2)
    :superlist
    :unequal))

(defn sublist [list1 list2]
  (if (window list2 list1)
    :sublist
    :unequal))

(defn classify [list1 list2]
  (cond (= list1 list2) :equal
        (> (count list1) (count list2)) (superlist list1 list2)
        (< (count list1) (count list2)) (sublist list1 list2)
        :else :unequal))
