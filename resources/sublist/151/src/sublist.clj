(ns sublist)

(defn check-equality [list1 list2]
  (let [len (count list1)]
    (if (and (= 0 (count list1)) (= 0 (count list2)))
      :equal
      (loop [i 0]
        (if (>= i len)
          :equal
          (if (not= (nth list1 i) (nth list2 i))
            :unequal
            (recur (inc i))))))))

(defn check-sublist [list1 list2]
  (if (= 0 (count list1))
    :sublist
    (let [d (- (count list2) (count list1)) l (count list1)]
      (loop [i 0]
        (if (> i d)
          :unequal
          (if (= :equal (check-equality list1 (subvec list2 i (+ i l))))
            :sublist
            (recur (inc i))))))
  ))

(defn classify [list1 list2]
  (cond
    (= (count list1) (count list2)) (check-equality list1 list2)
    (< (count list1) (count list2)) (check-sublist list1 list2)
    :else (if (= :sublist (check-sublist list2 list1)) :superlist :unequal)))