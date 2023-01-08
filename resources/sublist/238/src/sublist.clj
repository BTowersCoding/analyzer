(ns sublist)

;;cond is like an if statement
;;recur is like a loop
;;take returns a lazy sequence of the first n items in coll, or all items if there are fewer than n.

(defn- starts-with?
  "check if list2 starts with list1"
  [list1 list2]
  (let [len (count list1)]
    (= list1 (take len list2))))

(defn- sublist?
  [list1 list2]
  (cond
    (= (count list2) 0) false
    (starts-with? list1 list2) true
    :else (recur list1 (rest list2))))

(defn classify
  "Classify lists"
  [list1 list2]
  (cond
    (= list1 list2) :equal
    (sublist? list1 list2) :sublist
    (sublist? list2 list1) :superlist
    :else :unequal))