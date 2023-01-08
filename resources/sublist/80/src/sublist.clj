(ns sublist)

(defn sublist? [list1 list2]
  (cond
    (= '() list1) :sublist
    (= '() list2) :unequal
    (= (first list1) (first list2)) (sublist? (rest list1) (rest list2))
    :else :unequal))

(defn superlist? [list1 list2]
  (if (= :sublist (sublist? list2 list1)) :superlist :unequal))

(defn equal? [list1 list2]
  (cond (= '() list1) :equal
        (= (first list1) (first list2)) (equal? (rest list1) (rest list2))
        :else :unequal))

(defn classify [list1 list2] ;; <- arglist goes here
      (cond (> (count list1) (count list2)) (superlist? list1 list2)
            (< (count list1) (count list2)) (sublist? list1 list2)
            (= (count list1) (count list2)) (equal? list1 list2)))

;; [1 3] [1 2 3]
; sublist? list1 list 2
; sublist? [3] [2 3]
; sublist? [3] [3]
; sublist? [] []
