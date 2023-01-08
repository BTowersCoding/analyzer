(ns sublist)

(defn is-equal [list1 list2]
  (cond
        (not (== (count list1) (count list2))) false
        (and (== (count list1) 0) (== (count list2) 0)) true
        (== (first list1) (first list2)) (is-equal (rest list1) (rest list2))
        :else false))

(defn starts-with [list1 list2]
  (cond
    (== (count list1) 0) true
    (== (count list2) 0) false
    (== (first list1) (first list2)) (starts-with (rest list1) (rest list2))
    :else false))

(defn is-sublist [list1 list2]
    (cond (== (count list1) 0) true
          (== (count list2) 0) false
          (starts-with list1 list2) true
          :else (is-sublist list1 (rest list2))))

(defn classify [list1 list2]
      (cond
        (is-equal list1 list2) :equal
        (is-sublist list1 list2) :sublist
        (is-sublist list2 list1) :superlist
        :else :unequal))
