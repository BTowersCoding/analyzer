(ns sublist)

(require '[clojure.set :as set])

(defn sublist [list1 list2]
  "Checks whether list1 is a strict sublist of list2. If either are empty, this returns false."
    (cond
      (empty? list1) :sublist
      (empty? list2) :superlist
      :default (loop [x 0]
                 (if (neg? (- (count list2) (count list1) x))
                   false
                   (if (= list1 (subvec list2 x (+ x (count list1))))
                     true
                     (recur (inc x)))))))


(defn classify [list1 list2] ;; <- arglist goes here
  (cond
    (= list1 list2) :equal
    (empty? list1) :sublist
    (empty? list2) :superlist
    (sublist list1 list2) :sublist
    (sublist list2 list1) :superlist
    :default :unequal))

