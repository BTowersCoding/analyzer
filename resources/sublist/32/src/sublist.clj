(ns sublist)

(defn sublist? "true if a is sublist of b, otherwise false" [a b]
  (if (= nil (some #{a} (partition (count a) 1 b))) 
    false 
    true)
  )

(defn superlist? [a b]
  (sublist? b a)
  )

(defn classify [list1 list2] ;; <- arglist goes here
      (if (= list1 list2) 
        :equal 
        (if (sublist? list1 list2)
          :sublist
          (if (superlist? list1 list2)
            :superlist
            :unequal))
        )
)
