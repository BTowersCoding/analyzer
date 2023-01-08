(ns sublist)

(defn is-sublist [list1 list2]
  (loop [l2 list2]
      (if (empty? l2)
          false
          (if (->> l2
                   (take (count list1))
                   (= list1))
            true
            (recur (rest l2))))))

(defn classify [list1 list2] ;; <- arglist goes here
      ;; your code goes here
    (cond
      (and (empty? list1) (empty? list2)) :equal
      (and (empty? list1) (not (empty? list2))) :sublist
      (and (empty? list2) (not (empty? list1))) :superlist
      (= list1 list2) :equal
      (is-sublist list1 list2) :sublist
      (is-sublist list2 list1) :superlist
      :else :unequal
      ))
