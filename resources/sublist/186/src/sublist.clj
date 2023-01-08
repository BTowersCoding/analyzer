(ns sublist)

(defn isSublist? [l1 l2]
  (let [l1-length (count l1)]
    (loop [list2 l2]
      (if (< (count list2) l1-length)
        false
        (if (= (subvec list2 0 l1-length) l1)
          true
          (recur (vec (rest list2))))))))


(defn isSuperlist [l1 l2] 
  (isSublist? l2 l1))

(defn classify [list1 list2]
  (cond
    (= list1 list2) :equal
    (= (count list1) (count list2)) :unequal
    (and (< (count list1) (count list2)) (isSublist? list1 list2)) :sublist
    (and (> (count list1) (count list2)) (isSuperlist list1 list2)) :superlist
    :else :unequal))
