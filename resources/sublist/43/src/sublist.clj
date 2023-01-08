(ns sublist)

(defn prefix? [l1 l2]
  (= l1 (take (count l1) l2)))

(defn sub? [l1 l2]
  (->> (iterate rest l2)
       (take (inc (- (count l2) (count l1))))
       (map (partial prefix? l1))
       (some true?)))

(defn classify [list1 list2]
  (cond 
    (= (count list1) (count list2))
    (if (= list1 list2) :equal :unequal)
    
    (> (count list1) (count list2))
    ({:unequal :unequal
      :sublist :superlist} (classify list2 list1))
    
    :else
    (if (sub? list1 list2)
      :sublist
      :unequal))
)
