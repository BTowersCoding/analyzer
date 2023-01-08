(ns sublist)

(defn sublist? [list1 list2]
  (let [l1qtde (count list1)
        l2qtde (count list2)]
    (and (<= l1qtde l2qtde)
         (some #(= list1 %)
               (partition l1qtde 1 list2)))))

(defn classify [list1 list2]
  (cond
    (= list1 list2)        :equal
    (sublist? list1 list2) :sublist
    (sublist? list2 list1) :superlist
    :else                  :unequal))