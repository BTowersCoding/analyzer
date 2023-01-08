(ns sublist)

(defn in-list [item list] (some #(= item %) list))

(defn matchable-tuple-list [list1 list2] (partition (count list1) 1 list2))

(defn sublist-equals [list1 list2] 
  (cond
   (= list1 []) true 
    :else
     (in-list list1 (matchable-tuple-list list1 list2))))
                                                                 
(defn classify [list1 list2] 
  (cond
    (= list1 list2) :equal
    (sublist-equals list1 list2) :sublist
    (sublist-equals list2 list1) :superlist
    :else :unequal    
))
