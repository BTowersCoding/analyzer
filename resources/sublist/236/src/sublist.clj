(ns sublist)

(defn is-sublist
  [list-a list-b]
  (some (fn [list-test] (= list-test list-b))
        (distinct
         (for [start (range (count list-a))
               end (range (count list-a))]
           (take start (drop end list-a))))))

(defn equal-lists
  [list-a list-b]
  (= list-a list-b))

(defn classify [list-a list-b] ;; <- arglist goes here
  (cond
    (is-sublist list-a list-b) :superlist
    (is-sublist list-b list-a) :sublist
    (equal-lists list-a list-b) :equal
    :else :unequal))
