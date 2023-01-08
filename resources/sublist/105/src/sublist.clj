(ns sublist)

(defn classify [list1 list2]
  (let [is-in? (fn [l1 l2] (some #{l1} (partition (count l1) 1 l2)))]
    (cond
      (= list1 list2) :equal
      (is-in? list1 list2) :sublist
      (is-in? list2 list1) :superlist
      (not= list1 list2) :unequal
      )
    )
  )