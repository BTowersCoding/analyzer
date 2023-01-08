(ns sublist)

(defn subseq? [list1 list2]
  (some #{list1} (partition (count list1) 1 list2)))

(defn classify [list1 list2] ;; <- arglist goes here
  (cond
    (= list1 list2) :equal
    (subseq? list1 list2) :sublist
    (subseq? list2 list1) :superlist
    (empty? list1) :sublist
    (empty? list2) :superlist
    :else :unequal)
  
)
