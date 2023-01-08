(ns sublist)

(defn is-sublist? [list superlist]
  (->> (partition (count list) 1 superlist)
       (some #(= % list))))

(defn classify [list1 list2]
  (let [len1 (count list1)
        len2 (count list2)]
    (cond
      (= len1 len2) (if (= list1 list2) :equal :unequal)
      (< len1 len2) (if (is-sublist? list1 list2) :sublist :unequal)
      :else         (if (is-sublist? list2 list1) :superlist :unequal))))
