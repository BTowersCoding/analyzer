(ns sublist)

(defn rests [coll] (take-while seq (iterate rest coll)))

(defn is-prefix [list other]
  (= list (take (count list) other)))

(defn is-sublist [list other]
  (->> (rests other) (filter #(is-prefix list %1)) seq))

(defn classify [list other]
  (cond
    (= list other) :equal
    (and (< (count list) (count other)) (is-sublist list other)) :sublist
    (and (> (count list) (count other)) (is-sublist other list)) :superlist
    :else :unequal))