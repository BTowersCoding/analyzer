(ns sublist)


(defn- sublist?
  [list1 list2]
  (let [count1 (count list1)]
    (when (>= (count list2) count1)
      (or (= (subvec list2 0 count1) list1)
          (sublist? list1 (subvec list2 1))))))

(defn classify [list1 list2] ;; <- arglist goes
  (cond
    (= list1 list2) :equal
    (sublist? list1 list2) :sublist
    (sublist? list2 list1) :superlist
    :else :unequal))

