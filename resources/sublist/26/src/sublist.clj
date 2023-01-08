(ns sublist
  (:require [clojure.string :as str]))

(defn s [list]
  (str/join "-" list))

(defn classify [list1 list2]
  (let [string1 (s list1)
        string2 (s list2)]
    (cond
      (= string1 string2) :equal
      (str/includes? string1 string2) :superlist
      (str/includes? string2 string1) :sublist
      :else :unequal
      )
  ))