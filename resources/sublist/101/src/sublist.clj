(ns sublist)

(defn classify [list-a list-b] ;; <- arglist goes here
  (cond 
    (= list-a list-b) :equal
    (= [] list-a) :sublist
    (= [] list-b) :superlist
    (some #(= list-a %) (partition (count list-a) 1 list-b)) :sublist
    (some #(= list-b %) (partition (count list-b) 1 list-a)) :superlist
    :else :unequal
    )
)
