(ns sublist)

(defn classify
  [list1 list2]
  (cond
    (and
      (clojure.set/subset? list1 list2)
      (not= (count list2) 0)) :sublist
    (and
      (clojure.set/superset? list1 list2)
      (not= (count list1) 0)) :superlist
    (let [list-temp (distinct (apply conj list1 list2))]
      (and
        (= list-temp list1)
        (= list-temp list2))) :equal
    true :unequal))