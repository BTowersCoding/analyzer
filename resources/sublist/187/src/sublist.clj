(ns sublist
  [:require [clojure.string :as str]])

(defn classify [list1 list2] ;; <- arglist goes here
      ;; your code goes here
  (cond
    (= list1 list2) :equal
    (and (< (count list1) (count list2)) 
      (or
        (empty? list1)
        (str/includes? (str/join " " list2) (str/join " " list1)))) :sublist
    (and (> (count list1) (count list2)) 
      (or
        (empty? list2)
        (str/includes? (str/join " " list1) (str/join " " list2)))) :superlist
    :else :unequal)
)
