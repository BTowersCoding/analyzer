(ns sublist)


(defn in? 
  "true if coll contains elm"
  [elm coll]  
  (some #(= elm %) coll))


(defn sublist? [sublist superlist]
  (some #(= sublist %) (partition (count sublist) 1 superlist)))


(defn classify [a b]
  (cond (= a b) :equal
        (and (> (count a) (count b)) (sublist? b a)) :superlist
        (and (< (count a) (count b)) (sublist? a b)) :sublist
        :else :unequal))

 
;; (sublist? '(1 2 3) '(1 2 3 4))
;; (sequential? '(1 2 3))
