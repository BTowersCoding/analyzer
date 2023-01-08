(ns sublist)

(defn classify [list1 list2]
  (cond (= list1 list2) :equal
        (>= (java.util.Collections/indexOfSubList list1 list2) 0) :superlist
        (>= (java.util.Collections/indexOfSubList list2 list1) 0) :sublist
        :else :unequal))
