(ns sublist)

(defn contains-seq? [l1 l2]
  (some #{l1} (partition (count l1) 1 l2)))

(defn classify [list1 list2] ;; <- arglist goes here
      (cond 
        (= list1 list2) :equal
        (contains-seq? list1 list2) :sublist
        (contains-seq? list2 list1) :superlist
        :else :unequal))
