(ns sublist)

(defn subseq?
  [a b]
  (some #{a} (partition (count a) 1 b)))

(defn classify [list1 list2] ;; <- arglist goes here
  (cond
    (= list1 list2) :equal
    (subseq? list1 list2) :sublist
    (subseq? list2 list1) :superlist
    :else :unequal))
