(ns sublist)

(defn sublist?
  "Check whether the first argument is a sublist of the second argument"
  [slist llist]
  (some #(= % slist) (partition (count slist) 1 llist)))

(defn classify [list1 list2]
  (cond (= list1 list2) :equal
        (sublist? list1 list2) :sublist
        (sublist? list2 list1) :superlist
        :else :unequal))
