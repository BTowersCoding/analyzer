(ns sublist)

(defn contains-seq?
  [seq_a seq_b]
  (some #{seq_a} (partition (count seq_a) 1 seq_b)))

(defn classify [list1 list2]
        (cond
    (= list1 list2) (keyword "equal")
    (contains-seq? list1 list2) (keyword "sublist")
    (contains-seq? list2 list1) (keyword "superlist")
    :else
    (keyword "unequal"))
)
