(ns sublist)

(defn sublist? [list_1 list_2]
  (->> list_2
    (partition (count list_1) 1)
    (some #{list_1})))

(defn superlist? [list_2 list_1]
  (sublist? list_2 list_1))

(defn classify [list_1 list_2]
      (cond
        (= list_1, list_2) :equal
        (sublist? list_1 list_2) :sublist
        (superlist? list_2 list_1) :superlist
        :else :unequal))
