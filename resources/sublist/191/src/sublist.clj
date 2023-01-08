(ns sublist)

(def A [1 2 3])
(def B [1 2 3 4 5])

(partition (count A) 1 B)

(defn sublist? [a b]
  (some #(= a %) (partition (count a) 1 b)))

(defn superlist? [a b]
  (is-sublist b a))

(defn classify [list1 list2]
  (cond
    (= list1 list2) :equal
    (sublist? list1 list2) :sublist
    (superlist? list1 list2) :superlist
    :else :unequal))
