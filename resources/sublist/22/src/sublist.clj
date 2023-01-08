(ns sublist
      (:gen-class))

(defn classify [list1 list2] ;; <- arglist goes here
      ;; your code goes here
  (def l1 (clojure.string/replace (clojure.string/replace (str list1) #"\[" " ") #"\]" " "))
  (def l2 (clojure.string/replace (clojure.string/replace (str list2) #"\[" " ") #"\]" " "))
  (cond 
    
    (= (compare l1 l2) 0) :equal    
    ( and (= (count list1) 0) (> (count list2) 0)) :sublist
    ( and (= (count list2) 0) (> (count list1) 0)) :superlist
    (not= (count (clojure.string/replace l2 l1 "")) (count l2)) :sublist
    (not= (count (clojure.string/replace l1 l2 "")) (count l1)) :superlist
    (not= (compare l1 l2) 0) :unequal
   )
)
