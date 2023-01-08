(ns sublist
  (:require clojure.string
            [clojure.string :as s]))

(defn stringfy 
  "Takes a array and returns its contents as a string."
  [array]
  (-> array
      str
      (clojure.string/replace #"[\[\]]" "")))

(defn classify [list1 list2] ;; <- arglist goes here
  (let [str_list1 (stringfy list1)
        str_list2 (stringfy list2)]
    (cond 
      (= list1 list2) :equal
      (empty? list1) :sublist
      (empty? list2) :superlist
      (clojure.string/includes? str_list2 str_list1) :sublist
      (clojure.string/includes? str_list1 str_list2) :superlist
      :else :unequal)))

(classify [1 2 5] [0 1 2 3 1 2 5 6])
