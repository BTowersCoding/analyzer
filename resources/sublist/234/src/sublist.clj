(ns sublist)

(defn sublist [list1 list2]
  (->> list2 (partition (count list1) 1) (some #{list1}) some?)) 

(defn classify [list1 list2] ;; <- arglist goes here
  ;; your code goes here
  (cond
    (= list1 list2) :equal
    (sublist list1 list2) :sublist
    (sublist list2 list1) :superlist
    :else :unequal)
)