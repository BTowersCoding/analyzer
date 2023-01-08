(ns sublist)

(defn subseq? [list1 list2]
  (some #{list2} (partition (count list2) 1 list1)))

(defn classify [list1 list2]
  (if (= list1 list2) :equal
    (if (subseq? list2 list1) :sublist
      (if (subseq? list1 list2) :superlist :unequal))))
