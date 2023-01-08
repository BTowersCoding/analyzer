(ns sublist)

;; pseudocode
;; 1. check the length
;;    list1 > list2 -> :superlist/:unequal
;;    list1 = list 2 -> :equal/:unequal
;;    list 1 < list 2 -> :sublist/:unequal
;; 2. check nth of list1 , if equal to list2 , add up iter
;;   - if head is eqaul, then loop, if break return :unequal
;;   - if equal to the end , return :sublist

(defn classify [list1 list2] ;; <- arglist goes here
      ;; your code goes here
      (cond
        (= list1 list2) :equal
        (is_sublist list1 list2) :sublist
        :else :unequal
        )
)


(defn is_sublist? [list1 list2]
  (loop [iter 0]
    (let [upper (- count(list1) 1)]
         (when (< iter upper)
           (if-not (= (nth list1 iter) (nth list2 iter))
             false
             (recur (inc iter))
             )
           )
           true
         )
    )
  )

(is_sublist? '(1 2 3) '(1 2 3 4))
