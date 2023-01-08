(ns sublist)

(defn prefix-of?
  "Is list1 a prefix of list2?"
  [list1 list2]
  (if (empty? list1)
    true
    (if (or (empty? list2)
            (not= (first list1)
                  (first list2)))
      false
      (prefix-of? (rest list1)
                  (rest list2)))))

(defn sublist-of?
  "Is list1 a sublist of list2?"
  [list1 list2]
  (loop [l list2]
    (if (empty? l)
      (empty? list1)
      (or (prefix-of? list1 l)
          (recur (rest l))))))

(defn classify
  [list1 list2]
  (let [s12 (sublist-of? list1 list2)
        s21 (sublist-of? list2 list1)]
    (if s12
      (if s21
        :equal
        :sublist)
      (if s21
        :superlist
        :unequal))))
