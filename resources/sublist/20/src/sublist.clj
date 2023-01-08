(ns sublist)

(defn check-sublist [sub? sup-list]
  (loop [sup-rest sup-list]
    (let [count-sub? (count sub?)]
      (cond
        (> count-sub? (count sup-rest)) false
        (= sub? (take count-sub? sup-rest)) true
        :else (recur (rest sup-rest))))))

(defn classify [list1 list2]
  (cond
    (= list1 list2) :equal
    (check-sublist list1 list2) :sublist
    (check-sublist list2 list1) :superlist
    :else :unequal))
