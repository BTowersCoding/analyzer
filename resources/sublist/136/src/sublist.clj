(ns sublist)

(defn equals? [lst1 lst2]
  (= lst1 lst2))

(defn subseq? [lst1 lst2]
  (some #{lst1} (partition (count lst1) 1 lst2)))

(defn sublist? [lst1 lst2]
  (or (and (= lst1 list) (not (= lst2 list)))
      (subseq? lst1 lst2)
  ))

(defn superlist? [lst2 lst1]
  (or (and (= lst1 list) (not (= lst2 list)))
      (subseq? lst1 lst2)
  ))

(defn classify [list1 list2]
  (cond (equals? list1 list2) :equal
        (sublist? list1 list2) :sublist
        (superlist? list1 list2) :superlist
        :else :unequal))
