(ns sublist)

(defn sublist? [small big]
  "determines if small is sublist of big"
  (let [s       (first small)
        smallen (count small)
        biglen  (count big)]
    (if (> smallen biglen) false
      (some #(= small %) (for [x (range 0 (inc (- biglen smallen))) :let [y (take smallen (drop x big))] :when (= s (first y))] y)))))
    

(defn classify [list1 list2] ;; <- arglist goes here
  (cond
    (= list1 list2) :equal
    (sublist? list1 list2) :sublist
    (sublist? list2 list1) :superlist
    :else :unequal))
