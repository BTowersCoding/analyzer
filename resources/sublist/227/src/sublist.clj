(ns sublist)

(defn starts? [a b]
  (let [len (count a)] (= a (take len b))))

(defn sublist? [a b]
  (cond
    (= (count b) 0) false
    (starts? a b) true
    :else (recur a (rest b)))
)

(defn classify [list1 list2] 
      (cond
        (= list1 list2) :equal
        (= list1 []) :sublist
        (= list2 []) :superlist
        (sublist? list1 list2) :sublist
        (sublist? list2 list1) :superlist
        :else :unequal)
)
