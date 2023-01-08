(ns sublist)

(defn list-comparison
  [list1 list2] ;; <- list1 is longer
  (if (not= (count list1) (count list2))
    (if (= (take (count list2) list1) list2)
      true
      (list-comparison (drop 1 list1) list2))
    (= list1 list2)))

(defn classify
  [list1 list2] ;; <- arglist goes here, list1 = A, list2 = B
  (if (and (empty? list2) (not (= list1 list2)))
    :superlist
    (if (empty? list1)
      (if (empty? list2)
        :equal
        :sublist)
      (if (= (count list1) (count list2))
        (if (= list1 list2)
          :equal
          :unequal) ;; equal length code goes here
        (if (> (count list1) (count list2)) ;; <- if they are not of equal length, this happens
          (if (list-comparison list1 list2) ;; <- if A is bigger
            :superlist
            :unequal)
          (if (list-comparison list2 list1) ;; <- if B is bigger
            :sublist
            :unequal))))))