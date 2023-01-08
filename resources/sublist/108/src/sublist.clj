(ns sublist)

(defn sublist?
  [l1 l2] (some #{l1} (partition (count l1) 1 l2))
  )

(sublist? '(3) '(1 2))

(defn classify
  [l1 l2]
   (cond
     (= l1 l2) :equal
     (sublist? l1 l2) :sublist
     (sublist? l2 l1) :superlist
     :else :unequal
     )
  )
