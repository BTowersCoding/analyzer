(ns sublist)

(defn sublist-idx0
  "is s1 a sublist of s2 from the index 0"
  [l1 l2]
  (loop [s1 l1
         s2 l2]
    (let [f1 (first s1)
          f2 (first s2)]
      (cond
        (nil? f1) true
        (nil? f2) false
        (not= f1 f2) false
        :else (recur (rest s1) (rest s2))))))

(defn sublist
  "is s1 a sublist of s2"
  [l1 l2]
  (loop [s l2]
    (cond
      (< (count s) (count l1)) false
      (sublist-idx0 l1 s) true
      :else (recur (rest s)))))

(defn classify [list1 list2]
  (cond
    (= list1 list2) :equal
    (sublist list1 list2) :sublist
    (sublist list2 list1) :superlist
    :else :unequal))
