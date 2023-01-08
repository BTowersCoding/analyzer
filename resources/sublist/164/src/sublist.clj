(ns sublist)

(defn classify [list1 list2] ;; <- arglist goes here
  (cond
    (sublist/equal? list1 list2) :equal
    (sublist/sublist? list1 list2) :sublist
    (sublist/superlist? list1 list2) :superlist
    :else :unequal))

(defn- equal? [list1 list2]
  (= list1 list2))

(defn- sublist? [list1 list2]
  (cond
    (and (not-empty list1) (empty? list2)) false
    (= list1
       (first
        (partition
         (min (count list1) (count list2))
         list2))) true
    :else (recur list1 (rest list2))))

(defn- superlist? [list1 list2]
  (sublist? list2 list1))
