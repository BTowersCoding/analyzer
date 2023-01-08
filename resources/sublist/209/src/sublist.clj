(ns sublist
  (:require [clojure.set :as set]))

(defn classify [list1 list2]
  (cond
    (= list1 list2) :equal
    (set/subset? list1 list2) :sublist
    (set/superset? list1 list2) :superlist
    :else :unequal))