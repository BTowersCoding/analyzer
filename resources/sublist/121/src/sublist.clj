(ns sublist)

(defn contains-list 
  [bigger smaller]
  (some #{smaller} (partition (count smaller) 1 bigger)))

(defn classify [list1 list2]
  (cond (= list1 list2) :equal
        (contains-list list1 list2) :superlist
        (contains-list list2 list1) :sublist
        :default :unequal))
