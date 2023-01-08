(ns sublist)

(defn equal [list1 list2]
  "are lists equal?"
  (= list1 list2)
)

(defn sublist [list1 list2]
  "is list1 included in list2?"
  (loop [i 0]
    (if (< (- (count list2) i) (count list1))
      false ;; list1 does not fit any more
      (if (= list1 (subvec list2 i (+ i (count list1))))
        true
        (recur (inc i))
      )
    )
  )
)

(defn classify [list1 list2] ;; <- arglist goes here
      ;; your code goes here
  (if (equal list1 list2) :equal 
    (if (sublist list1 list2) :sublist 
      (if (sublist list2 list1) :superlist :unequal))))
