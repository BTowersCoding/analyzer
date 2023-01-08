(ns sublist)

(defn is-sublist?
  ([list1 list2] (is-sublist? list1 list2 0 0))
  ([list1 list2 n m]
   (if (> (- (count list1) n) (- (count list2) m))
     false
     (if (= (count list1) n)
       true
       (if (= (count list2) m)
         false
         (if (= (nth list1 n) (nth list2 m))
           (recur list1 list2 (+ n 1) (+ m 1))
           (recur list1 list2 0 (- m (- n 1)))))))))

(defn classify [list1 list2] ;; <- arglist goes here
  (let [size1 (count list1),
        size2 (count list2)]
    (if (and (< size1 size2) (is-sublist? list1 list2))
      :sublist
      (if (and (> size1 size2) (is-sublist? list2 list1))
        :superlist
        (if (and (= size1 size2) (is-sublist? list1 list2))
          :equal
          :unequal)))))