(ns sublist)

(defn classify [list1 list2] ;; <- arglist goes here
      ;; your code goes here
  (cond
    (= list1 list2) :equal
    (some #{list1} (partition (count list1) 1 list2)) :sublist
    ((some #{list2} (partition (count list2) 1 list1))) :superlist
    :else :unequal))

(comment
  (defn subseq? [a b]
    (some #{a} (partition (count a) 1 b)))
  (cond)
  (defn superseq? [b a]
    (some #{a} (partition (count a) 1 b)))

  (subseq? [1 2 3] [1 2 3 4 5 6])
  (subseq? [1 2 3] [1 2 3])
  (superseq? [1 2 3 4] [1 2])

  (partition 3 1 [1 2 3 4 5 6]))