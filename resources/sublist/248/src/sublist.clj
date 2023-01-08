(ns sublist)

(defn sublist [l1 l2]
  (if (= 0 (count l1)) true
      (if (= 0 (count l2)) false
          (or
           (= l1 l2)
           (sublist l1 (rest l2))
           (sublist l1 (butlast l2))))))

(defn classify [l1 l2]
  (if (= l1 l2) :equal
      (if (sublist l1 l2) :sublist
          (if (sublist l2 l1) :superlist :unequal))))
