(ns sublist)

(defn- sublist? [list1 list2]
  (let [n (count list1)]
    (loop [list2 list2]
      (cond
        (empty? list2)
        false

        (= list1
           (take n list2))
        true

        :else
        (recur (rest list2))))))

(defn classify [list1 list2]
  (cond
    (= list1 list2)
    :equal

    (and
      (empty? list1)
      (seq list2))
    :sublist

    (and
      (seq list1)
      (empty? list2))
    :superlist

    (> (count list1)
       (count list2))
    (if (sublist? list2 list1)
      :superlist
      :unequal)

    :else
    (if (sublist? list1 list2)
      :sublist
      :unequal)))
