(ns sublist)

(defn sublist [list1 list2]
      (loop [l2 list2]
            (cond
                  (< (count l2) (count list1)) false
                  (= (subvec l2 0 (count list1)) list1) true
                  :else (recur (subvec l2 1 (count l2))))))

(defn classify [list1 list2] ;; <- arglist goes here
      (cond 
            (= list1 list2) :equal
            (sublist list1 list2) :sublist
            (sublist list2 list1) :superlist
            :else :unequal))
