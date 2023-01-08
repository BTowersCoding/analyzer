(ns sublist)

(defn a-sub-b
  [a b]
  ;; assume `a` is shorter than `b`;
  ;; b ~ [optional elements]a[optional elements]
  ;; 1) skipwhile b[i] != a[0]
  ;; 2) check if the rest of b starts with the a
  ;; 3) don't care about the rest
  (let [a0 (first a)
        b-rest (drop-while #(not= a0 %) b)
        zipped (map vector a b-rest)]
    (every? = zipped)))

(defn classify
  [list1 list2]
  (let [len1 (count list1)
        len2 (count list2)]
    (cond (= len1 len2) (if (a-sub-b list1 list2)
                          :equal
                          :unequal)
          (< len1 len2) (if (a-sub-b list1 list2)
                          :sublist
                          :unequal)
          (> len1 len2) (if (a-sub-b list2 list1)
                          :superlist
                          :unequal))))
