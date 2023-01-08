(ns sublist)

(defn- sublist? [a b]
  (some #{a} (partition (count a) 1 b)))

(defn classify [a b] ;; <- arglist goes here
  (cond
    (= a b) :equal
    (sublist? a b) :sublist
    (sublist? b a) :superlist
    :else :unequal))