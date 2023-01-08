(ns sublist)

(defn same-from-beginning?
  "Return true when list1 and list2 have the same elements from the beginning"
  [list1 list2]
  (->> (map = list1 list2)
       (every? true?)))

(defn same-size-or-larger?
  "Return true when list2 is same size or larger than list1"
  [list1 list2]
  (>= (count list2)
      (count list1)))

(defn sublist?
  "Return true if list1 is sublist of list2"
  [list1 list2]
  (when (same-size-or-larger? list1 list2)
    (or (same-from-beginning? list1 list2)
        (sublist? list1 (rest list2)))))

(defn classify [list1 list2] ;; <- arglist goes here
  (cond
    (= list1 list2)
    :equal
    
    (sublist? list1 list2)
    :sublist
    
    (sublist? list2 list1)
    :superlist
    
    :else
    :unequal))
