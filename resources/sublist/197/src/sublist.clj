(ns sublist)

(defn ^:private subseq?
  [list1 list2]
  (let [partitions-count (count list1)
        subseqs-of-list2 (partition partitions-count 1 list2)]
    (some #{list1} subseqs-of-list2)))

(defn classify [list1 list2]
   (cond
     (= list1 list2)         :equal
     (subseq? list1 list2)   :sublist
     (subseq? list2 list1)   :superlist
     :else                   :unequal))
