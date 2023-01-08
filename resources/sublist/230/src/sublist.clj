(ns sublist)

(defn sublist? [list1 list2]
  (let [ll1 (count list1)
        ll2 (count list2)]
    (and (<= ll1 ll2)
         (some #(= list1 %)
               (partition ll1 1 list2)))))

(defn classify [list1 list2]
  (cond
    (= list1 list2)        :equal
    (sublist? list1 list2) :sublist
    (sublist? list2 list1) :superlist
    :else                  :unequal))
