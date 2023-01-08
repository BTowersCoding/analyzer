(ns sublist)

(defn classify [list1 list2] ;; <- arglist goes here
      ;; your code goes here
  (cond
    (= list1 list2) :equal
    (clojure.set/subset? list1 list2) :sublist
    (clojure.set/subset? list2 list1) :superlist
    :else :unequal
  )
)
