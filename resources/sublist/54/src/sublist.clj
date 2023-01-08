(ns sublist)

(defn subseq? [a b]
  (if (= a (some #{a} (partition (count a) 1 b)))
    true
    false ))

(defn classify [list1 list2] ;; <- arglist goes here
      ;; your code goes here
  (cond
    (= list1 list2) :equal
    (subseq? list1 list2) :sublist
    (subseq? list2 list1) :superlist
    :else :unequal))

