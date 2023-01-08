(ns sublist
  (:use [clojure.set :only [superset?]]))

(defn classify [list1 list2]
  (cond
    (= list1 list2) :equal
    (superset? list2 list1) :sublist
    (superset? list1 list2) :superlist
    :else :unequal))
