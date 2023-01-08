(ns sublist)
(defn is-sublist? [l1 l2]
  (some true? 
        (map #(= l1 %) (partition (count l1) 1 l2))) )

(defn is-superlist? [l1 l2]
  (some true? 
        (map #(= l2 %) (partition (count l2) 1 l1))) )

(defn classify [list1 list2] ;; <- arglist goes here
      ;; your code goes here
  (cond (= list1 list2) :equal
        (is-superlist? list1 list2) :superlist
        (is-sublist? list1 list2) :sublist
        :else :unequal
    
  ))
