(ns sublist)

(defn classify [a b] 
  (let [x (set a)
        y (set b)]
    (cond
      (= x y) :equal
      (clojure.set/subset? x y) :sublist
      (clojure.set/subset? y x) :superlist
      :else :unequal)))
