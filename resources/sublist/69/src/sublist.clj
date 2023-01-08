(ns sublist)

(defn starts-with?
  "check if list2 starts with list1"
  [list1 list2]
  (let [len (count list1)]
    (= list1 (take len list2))))

(defn sublist?
  [list1 list2]
  (cond
    (= (count list2) 0) false
    (starts-with? list1 list2) true
    :else (recur list1 (rest list2))))

(defn classify [list1 list2]
  (cond 
    (= list1 list2) :equal
    (sublist? list1 list2) :sublist
    (sublist? list2 list1) :superlist
    :else :unequal)
  )
