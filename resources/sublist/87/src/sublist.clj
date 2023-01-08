(ns sublist)


(defn classify [list1 list2] ;; <- arglist goes here
      ;; your code goes here
  (cond
    (= list1 list2) :equal
    (> (count list1) (count list2)) (cond 
                                      (= [] list2) :superlist
                                      (= (filter #(contains? list1 %) list2) list2) :superlist
                                      :else :unequal)
    (< (count list1) (count list2)) (cond 
                                      (= [] list1) :sublist
                                      (= (filter #(contains? list2 %) list1) list1) :sublist
                                      :else :unequal)
    :else :unequal)
)

