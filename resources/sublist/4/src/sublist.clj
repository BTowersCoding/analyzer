(ns sublist)

(defn sublist?-
  [list-a list-b]
      (some #(= list-a %) (partition (count list-a) 1 list-b)))

(defn classify [list1 list2] ;; <- arglist goes here
  ;; your code goes here
  (cond
    (= list1 list2)
    :equal
    (sublist?- list1 list2)
    :sublist
    (sublist?- list2 list1)
    :superlist
    :else
    :unequal))