(ns sublist)

(defn classify [list1 list2] ;; <- arglist goes here
      ;; your code goes here
  (cond
    (every? empty? [list1 list2]) :equal
    (empty? list1) :superlist
    (empty? list2) :sublist))
