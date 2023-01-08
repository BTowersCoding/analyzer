(ns sublist)
(declare contained?)

(defn classify [list1 list2]
          (cond (= list1 list2) :equal
                (contained? list1 list2) :sublist
                (contained? list2 list1) :superlist
                 :else :unequal))

(defn contained? [arr ver]
   (cond (empty? ver) false 
          :else (let [ c (count arr)
                       r (rest ver)
                       p (partition c ver)]
   (if (nil? (some #{arr} p)) (recur arr r) true))))