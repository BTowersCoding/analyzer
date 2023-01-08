(ns sublist
  (:require [clojure.string :as s]))

(defn stringify
  "unwraps a coll and converts vals into a str"
  [coll]
  (s/join " " coll))

(defn s-sublist?
  "boolean that determines of list1 is a sublist of list2"
  [list1 list2]
  (let [str1 (stringify list1) str2 (stringify list2)]
    (s/includes? str2 str1)))

(defn classify
  "evaluates two lists/vectors and determines if A is a sublist, superlist,
     equal, or unequal list of B"
  [A B]
  (if (= A B)
    :equal
    (if (s-sublist? A B)
      :sublist
      (if (s-sublist? B A)
        :superlist
        :unequal))))