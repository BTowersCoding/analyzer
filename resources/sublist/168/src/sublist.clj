(ns sublist)

(defn equal [xs ys]
  (loop [xs xs
         ys ys]
    (cond
      (and (empty? xs) (empty? ys)) true
      (= (first xs) (first ys)) (recur (rest xs) (rest ys))
      :else false)))

(defn sublist [xs ys]
  (loop [xs xs
         ys ys]
    (cond
      (> (count xs) (count ys)) false
      (equal xs (take (count xs) ys)) true
      :else (recur xs (rest ys)))))

(defn superlist [xs ys]
  (loop [xs xs]
    (cond
      (< (count xs) (count ys)) false
      (sublist ys xs) true
      :else (recur (rest xs) ys))))

(defn classify [list1 list2] ;; <- arglist goes here
      ;; your code goes here
  (cond
    (equal list1 list2) :equal
    (sublist list1 list2) :sublist
    (superlist list1 list2) :superlist
    :else :unequal))
