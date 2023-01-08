(ns sublist)

(defn sublist? 
  ([l1 l2]
   (some true? (sublist? l1 l2 (list))))
  ([l1 l2 l3]
  (if (empty? l2)
    l3
    (sublist? l1 (rest l2) (conj l3 (= l1 (take (count l1) l2)))))))

(defn classify [list1 list2]
    (cond
      (= list1 list2)
      :equal
      (sublist? list1 list2)
      :sublist
      (sublist? list2 list1)
      :superlist
      :else
      :unequal))