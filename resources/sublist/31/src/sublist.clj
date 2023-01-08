(ns sublist)

(defn a-contains-b
  [a b]
  (not 
   (not-any? #(= % b) (partition (count b) 1 a))))

(defn classify [a b] ;; <- arglist goes here
      (if (= a b)
        :equal
        (if (a-contains-b a b)
          :superlist
          (if (a-contains-b b a)
            :sublist
            :unequal))))

