(ns sublist)

(defn classify [list1 list2] ;; <- arglist goes here
  (let [first-count (count list1)
        second-count (count list2)
        check-loop
        (fn [l1 l2 key]
          (let [l1-count (count l1)
              l2-count (count l2)]
            (if (contains? (set (for [i (range l2-count)]
                               (when (and (<= (+ i l1-count)  l2-count) (= (subvec l2 i (+ l1-count i)) l1))
                                 key))) key) key
                     :unequal)))]
    (cond
      (= list1 list2) :equal
      (= list1 []) :sublist
      (= list2 []) :superlist
      (= first-count second-count) :unequal
      (< first-count second-count) (check-loop list1 list2 :sublist)
      (> first-count second-count) (check-loop list2 list1 :superlist)
      :else :unequal)))
