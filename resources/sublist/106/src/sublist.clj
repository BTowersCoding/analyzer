(ns sublist)

(defn is_subvec [list1 list2]
  (let [n (count list1)
        m (count list2)]
    (loop [idx 0]
      (cond
        (> (+ idx n) m) false
        (= list1 (subvec list2 idx (+ idx n))) true
        :else (recur (inc idx))))))

(defn classify [list1 list2] 
  (let [n (count list1)
        m (count list2)]
    (cond
      (< n m) (if (is_subvec list1 list2) :sublist :unequal) 
      (> n m) (if (is_subvec list2 list1) :superlist :unequal) 
      (= n m) (if (= list1 list2) :equal :unequal))))
