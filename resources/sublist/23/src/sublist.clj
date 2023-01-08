(ns sublist)

(defn sublist? [list1 list2] ;; <- arglist goes here
  (let [amount (count list1)]
    (loop [l list2]
      (cond
        (< (count l) amount) false
        (= list1 (take amount l)) true
        :else (recur (rest l)))))
  )

(defn classify [list1 list2]
  (cond
    (= list1 list2) :equal
    (sublist? list1 list2) :sublist
    (sublist? list2 list1) :superlist
    :else :unequal))
