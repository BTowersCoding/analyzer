(ns sublist
  (:require [clojure.string :as str]))
(use 'clojure.data)


(defn classify [list1 list2] ;; <- arglist goes here
  (let [x (diff list1 list2)]
    (cond
      (and (empty? list1) (empty? list2)) :equal
      (= list1 list2) :equal
      (str/includes? (str/join \, list2) (str/join \, list1)) :sublist
      (str/includes? (str/join \, list1) (str/join \, list2)) :superlist
      (and (nil? (nth x 0)) (nil? (nth x 1)) (= (nth x 2) list1)) :equal
      (and (empty? list1) (not (empty? list2))) :sublist
      (and (not (empty? list1)) (empty? list2)) :superlist
      (and (nil? (nth x 0)) (= (nth x 2) list1)) :sublist
      (and (nil? (nth x 1)) (= (nth x 2) list2)) :superlist
      :else :unequal)))
