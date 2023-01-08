(ns sublist)

(defn sublist? [list1 list2]
  (let [l1-length (count list1)]
    (loop [l2 list2]
      (if (< (count l2) l1-length)
        false
        (if (= (subvec l2 0 l1-length) list1)
          true
          (recur (vec (rest l2))))))))

(defn superlist? [list1 list2]
  (sublist? list2 list1))

(defn equals? [list1 list2]
  (and (superlist? list1 list2) (sublist? list1 list2))
)

(defn classify [list1 list2] ;; <- arglist goes here
  (cond
    (equals? list1 list2) :equal
    (sublist? list1 list2) :sublist
    (superlist? list1 list2) :superlist
    :else :unequal))
