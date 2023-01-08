(ns sublist)

(defn list-equals
  [[list1-head & list1-rest] [list2-head & list2-rest]]
  (if (and (empty? list1-rest) (empty? list2-rest))
    (= list1-head list2-head)
    (and (= list1-head list2-head) (recur list1-rest list2-rest))))

(defn sublist
  [list sublist]
  (if (>= (count list) (count sublist)) ;;check if sublist would fit
    (if (= 0 (compare (into [] (take (count sublist) list)) sublist)) ;;cut off elements in the end and compare
      true
      (recur (rest list) sublist)) ;;try from next index
    false
    )
  )

;;:sublist, :superlist, :equal or:unequal
(defn classify [list1 list2] ;; <- arglist goes here
  (cond
    (list-equals list1 list2) :equal
    (sublist list1 list2)  :superlist
    (sublist list2 list1) :sublist
    :else :unequal
    )
  )
