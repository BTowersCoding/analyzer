(ns sublist)

(defn sublist? [l1 l2]
  (if (> (count l1) (count l2)) false
    (let [subs (into #{} (partition (count l1) 1 l2))]
      (subs l1))))

(defn superlist? [l1 l2]
  (sublist? l2 l1))


(defn classify [list1 list2] ;; <- arglist goes here
      (cond (= list1 list2)  :equal
            (sublist? list1 list2) :sublist
            (superlist? list1 list2) :superlist
            :else   :unequal)
)
