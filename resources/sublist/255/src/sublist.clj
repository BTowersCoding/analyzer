(ns sublist
    (:require [clojure.string 
               :refer [join includes?]]))

(defn classify [list1 list2] ;; <- arglist goes here
      ;; your code goes here
  (let [s1 (join "," list1)
      s2 (join "," list2)]
    (cond 
        (= s1 s2) :equal
        (includes? s1 s2) :superlist
        (includes? s2 s1) :sublist
        :else :unequal)
    )
)
