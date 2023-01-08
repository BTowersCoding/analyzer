(ns sublist)

(defn sublist? [list1 list2]
  (let [partition-size (count list1)
        partitions (partition partition-size 1 list2)]
    (some #{list1} partitions)))

(defn classify [list1 list2] ;; <- arglist goes here
  (cond
    (= list1 list2) :equal
    (sublist? list1 list2) :sublist
    (sublist? list2 list1) :superlist
    :else :unequal))