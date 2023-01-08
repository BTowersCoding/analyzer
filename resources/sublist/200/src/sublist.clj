(ns sublist
  (:require [clojure.set :as set]))

(defn is-sublist? [list1 list2]
  (some #(= % list1) (partition (count list1) 1 nil list2)))

(defn classify [list1 list2]
  (if (= list1 list2)
    :equal
    (if (is-sublist? list1 list2)
      :sublist
      (if (is-sublist? list2 list1)
        :superlist
        :unequal))))