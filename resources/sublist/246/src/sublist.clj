(ns sublist)

(defn contains-rest [list1 list2]
(if (= (count list2) 0) true
(if (= (count list1) 0) false
(and 
    (= (first list1) (first list2))
    (contains-rest (rest list1) (rest list2))
))))

(defn suplist [list1 list2]
(if (< (count list1) (count list2))
false
(if
(contains-rest list1 list2)
true
(suplist (rest list1) list2)
)
))


(defn classify [list1 list2]
(if (= list1 list2) :equal
(if (suplist list1 list2) :superlist
(if (suplist list2 list1) :sublist
:unequal
)

)
)
)
