(ns sublist)

(defn is-sublist?
  [list1 list2]
  (let [part-size (count list1)]
    (some #{list1} (partition part-size 1 list2))))

(defn classify
  "Classifies the relation of the first argument to the second one.
  Returns one of :equal, :sublist, :superlist or :unequal."
  [list1 list2] ;; <- arglist goes here
  (cond
    (= list1 list2) :equal
    (is-sublist? list1 list2) :sublist
    (is-sublist? list2 list1) :superlist
    :else :unequal))

