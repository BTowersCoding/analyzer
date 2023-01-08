(ns sublist)

(defn subseq?
  [x y]
  (some #{x} (partition (count x) 1 y)))

(defn classify [list1 list2]
     (cond 
       (= list1 list2) :equal
       (subseq? list1 list2) :sublist
       (subseq? list2 list1) :superlist
       :else :unequal))
