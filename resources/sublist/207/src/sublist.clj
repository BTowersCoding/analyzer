(ns sublist)

(defn equal? [list1 list2]
  (= list1 list2))

(defn sublist? [sub full]
  (cond
    (equal? sub full) true
    (> (count sub) (count full)) false
    :else (or (sublist? sub (rest full)) (sublist? sub (butlast full)))))

(defn classify [list1 list2]
  (cond
    (equal? list1 list2) :equal
    (sublist? list1 list2) :sublist
    (sublist? list2 list1) :superlist
    :else :unequal))