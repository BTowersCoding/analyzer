(ns sublist)
(require '[clojure.set :as s])

(defn contains-list? [list1 list2]
  (pos?
   (count
    (filter (fn [x] (= x list1))
      (map (fn [y] (subvec list2 y (+ y (count list1)))) (range (- (count list2) (dec (count list1)))))))))

(defn classify [list1 list2] ;; <- arglist goes here
      ;; your code goes here

  (or
   (and (= list1 list2) :equal)
   (and (contains-list? list1 list2) :sublist)
   (and (contains-list? list2 list1) :superlist)
   :unequal
   )
  )
