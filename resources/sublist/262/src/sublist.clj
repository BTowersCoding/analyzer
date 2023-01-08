(ns sublist)

(defn- list-is-equal? [as bs]
  (every? true? (for [i (range (count as))] (= (get as i) (get bs i)))))

(defn- list-is-sublist? [as bs]
  (cond
    (empty? as) true
    (> (count as) (count bs)) false
    (list-is-equal? as bs) true
    :else (recur as (subvec bs 1))))

(defn classify [list1 list2]
  (cond
    (and (= (count list1) (count list2)) (list-is-equal? list1 list2)) :equal
    (and (< (count list1) (count list2)) (list-is-sublist? list1 list2)) :sublist
    (list-is-sublist? list2 list1) :superlist
    :else :unequal))
