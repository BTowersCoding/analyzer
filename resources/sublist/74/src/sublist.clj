(ns sublist)

(defn classify [list1 list2]
  (let [s (apply str list1) c (count s) s2 (apply str list2) c2 (count s2)]
    (cond
      (= list1 list2) :equal
      (and (re-find (re-pattern s) s2) (> c2 c)) :sublist
      (and (re-find (re-pattern s2) s) (> c c2)) :superlist
      :else :unequal)))
