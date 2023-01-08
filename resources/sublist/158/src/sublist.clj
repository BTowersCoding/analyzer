(ns sublist)

(defn classify [list1 list2] ;; <- arglist goes here
  (cond
    (= list1 list2) :equal
    (clojure.set/subset? list1 list2) :sublist
    (clojure.set/superset? list1 list2) :superlist
    :else :unequal)
)
