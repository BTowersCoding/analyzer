(ns sublist)

(defn sublist? [a b] (some #{a} (partition (count a) 1 b)))

(defn classify [list1 list2] ;; <- arglist goes here
      (cond (= list1 list2) :equal
            (sublist? list1 list2) :sublist
            (sublist? list2 list1) :superlist
            :default :unequal
            )) ;; your code goes here

;; (= '(0 1 2) '(0 1 2))

