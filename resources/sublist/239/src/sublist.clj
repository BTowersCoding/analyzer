(ns sublist)

(defn superlist? [[super-head & super-tail] [sub-head & sub-tail :as sublist]]
  (let [heads-equal? (= super-head sub-head)]
    (cond
      (empty? sub-tail)   heads-equal?
      (empty? super-tail) false
      :else               (or (and heads-equal?
                                   (superlist? super-tail sub-tail))
                              (superlist? super-tail sublist)))))

(defn classify [list1 list2]
  (let [one-encloses? (or (empty? list2) (trampoline superlist? list1 list2))
        two-encloses? (or (empty? list1) (trampoline superlist? list2 list1))]
    (cond
      (and one-encloses?
           two-encloses?) :equal
      one-encloses?       :superlist
      two-encloses?       :sublist
      :else               :unequal)))
