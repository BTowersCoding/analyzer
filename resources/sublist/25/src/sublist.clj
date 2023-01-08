(ns sublist)

(def invert {:equal     :equal
             :unequal   :unequal
             :sublist   :superlist
             :superlist :sublist})

(defn classify [list1 list2]
  (cond
    (= list1 list2)                 :equal
    (= (count list1) (count list2)) :unequal
    (empty? list1)                  :sublist
    (empty? list2)                  :superlist
    (> (count list1) (count list2)) (invert (classify list2 list1))
    :else (if (some #(= list1 %) (partition (count list1) 1 list2))
            :sublist
            :unequal)))
