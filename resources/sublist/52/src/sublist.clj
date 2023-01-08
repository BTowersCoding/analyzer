(ns sublist)

(defn subseq?
  "https://stackoverflow.com/a/21538308/1336774"
  [a b]
  (some #{a} (partition (count a) 1 b)))

(defn classify [list1 list2]
  (let [length1 (count list1)
        length2 (count list2)]
    (if (= length1 length2)
      (if (every? true? (map = list1 list2))
        :equal :unequal)
      (if (< length1 length2)
        (if (subseq? list1 list2)
          :sublist :unequal)
        (if (subseq? list2 list1)
          :superlist :unequal)))))
