(ns sublist)


;Using subvec (https://clojuredocs.org/clojure.core/subvec
#_(defn sublist? [l1 l2]
  (some 
    #(= l1 %)
    (map
	  #(subvec l2 % (+ % (count l1)))
	  (range (- (count l2) (- (count l1) 1))))))

;Using take (https://clojuredocs.org/clojure.core/take)
#_(defn sublist? [l1 l2]
  (some 
    #(= l1 %)
    (map
	  #(take (count l1) (drop % l2))
	  (range (- (count l2) (- (count l1) 1))))))

;Using partition (https://clojuredocs.org/clojure.core/partition)
(defn sublist? [l1 l2]
  (some #(= l1 %) (partition (count l1) 1 l2)))


(defn classify [l1 l2] ;; <- arglist goes here
  ;; your code goes here
  (cond
    (= l1 l2) :equal
    (sublist? l1 l2) :sublist
    (sublist? l2 l1) :superlist
    :else :unequal))
