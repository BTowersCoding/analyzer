(ns sublist)

(defn sublist? [list1 list2]
  (some #{list1} (partition (count list1) 1 list2)))

(defn classify [list1 list2]
  (let [c1 (count list1) c2 (count list2)]
    (cond
      (every? empty? [list1 list2]) :equal
      (= c1 c2) (if (sublist? list1 list2) :equal :unequal)
      (> c1 c2) (if (sublist? list2 list1) :superlist :unequal)
      (< c1 c2) (if (sublist? list1 list2) :sublist :unequal))))
