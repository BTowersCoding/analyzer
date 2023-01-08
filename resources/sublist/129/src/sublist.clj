(ns sublist
  (:require [clojure.set :as set]))

(defn classify [list1 list2] ;; <- arglist goes here
      ;; your code goes here
  (let [[longer shoter] (if (<= (count list1) (count list2))
                          [list2 list1]
                          [list1 list2])]
    (if (= (count longer) (count shoter))
           (if (= longer shoter)
             :equal
             :unequal)
           (if (some #(= shoter %) (partition (count shoter) 1 longer))
             (if (= longer list1)
               :superlist
               :sublist)
             :unequal))))
