(ns sublist)

(defn- starts-with? [col sub]
  (cond
    (empty? sub) true
    (empty? col) false
    (= (first col) (first sub)) (starts-with? (rest col) (rest sub))
    :else false))

(defn- sublist? [super sub]
  (cond
    (empty? sub) true
    (empty? super) false
    (starts-with? super sub) true
    :else (sublist? (rest super) sub)))

(defn classify [list1 list2] ;; <- arglist goes here
      ;; your code goes here
  (cond
    (= list1 list2) :equal
    (sublist? list1 list2) :superlist
    (sublist? list2 list1) :sublist
    :else :unequal))
