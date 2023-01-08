(ns sublist)

(defn- sublist [list start end]
  (drop start (take end list)))

(defn- is-sublist? [list1 list2]
  (let [size (count list1)
        n (- (count list2) size)]
    (loop [i 0]
      (if (= list1 (sublist list2 i (+ i size)))
        true
        (if (< i n)
          (recur (inc i))
          false)))))

(defn classify [list1 list2]                                ;; <- arglist goes here
  (let [size-list1 (count list1)
        size-list2 (count list2)]
    (cond
      (= list1 list2) :equal
      (and (< size-list1 size-list2) (is-sublist? list1 list2)) :sublist
      (and (< size-list2 size-list1) (is-sublist? list2 list1)) :superlist
      :else :unequal)))
