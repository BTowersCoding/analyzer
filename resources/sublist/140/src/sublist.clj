(ns sublist)

(defn contains-element? [list element]
  (some #(= element %) list))

(defn lists-equal? [list1 list2]
  (and (= (count list1)
          (count list2))
       (every? true? (mapv = list1 list2))))

(defn sublist? [a b]
  (and (< (count a) (count b))
       (or (= [] a)
           (and (some #(= (first a) %) b)
                (some #(lists-equal? a %)
                      (keep-indexed
                       #(if (= (first a) %2)
                          (take (count a) (nthrest b %1)))
                       b))))))

(defn classify [list1 list2] ;; <- arglist goes here
  ;; your code goes here
  (cond
    (lists-equal? list1 list2) :equal
    (sublist? list1 list2) :sublist
    (sublist? list2 list1) :superlist
    :else :unequal))
