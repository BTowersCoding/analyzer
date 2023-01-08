(ns sublist)

(defn contained-in [a b]
  ;; returns true if list a contained in list b
  (let [len-a (count a), len-b (count b)]
    (if
      (< len-b len-a) false
      (if (= a (take len-a b)) true
        (contained-in a (rest b))))))

(defn classify [list1 list2] ;; <- arglist goes here
  (cond
    (= list1 list2)            :equal
    (contained-in list2 list1) :superlist
    (contained-in list1 list2) :sublist
    :else                      :unequal
  ))
