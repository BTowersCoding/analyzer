(ns sublist)


(defn sublist? [list1 list2]
  (if (< (count list2) (count list1))
    false
    (loop [list1-n 0
           list2-n 0]
      
      (if (< (- (count list2) (+ 1 list2-n)) (- (count list1) (+ 1 list1-n)))
        false
        (if (= (nth list1 list1-n)
               (nth list2 list2-n))
          (if (= list1-n (- (count list1) 1))
            true
            (recur (inc list1-n)
                   (inc list2-n)))
          (recur 0
                 (inc (- list2-n list1-n)))
          )))))

(defn classify [list1 list2] ;; <- arglist goes here
  (cond
    (= list1 list2) :equal
    (or (empty? list1) (sublist? list1 list2)) :sublist
    (or (empty? list2) (sublist? list2 list1)) :superlist
    :else :unequal)
  )


