(ns sublist)

(defn is-prefix? [list1 list2]
  (cond (empty? list1) true
        (empty? list2) false
        (= (first list1) (first list2)) (recur
                                         (rest list1)
                                         (rest list2))
        :else false))

(defn is-sublist? [list1 list2]
  (some (partial is-prefix? list1)
        (map #(drop % list2) (range 0 (inc (count list2))))))


(defn classify [list1 list2]
  (let [sub (is-sublist? list1 list2)
        sup (is-sublist? list2 list1)
        eq (and sub sup)]
    (cond eq :equal
          sub :sublist
          sup :superlist
          :else :unequal)))


