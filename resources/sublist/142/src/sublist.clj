(ns sublist)


(defn sublist
  [short-list long-list]
  (let [short-list-length (count short-list)]
    (loop [l-list long-list]
      (let [chunk (take short-list-length l-list)]
        (if (< (count chunk) short-list-length) false
            (if (= chunk short-list) true
                (recur (rest l-list))))))))


(defn classify [list1 list2]
  (let [list1-length (count list1)
        list2-length (count list2)]
    (cond
      (= list1-length list2-length) (if (= list1 list2) :equal :unequal)
      
      (< list1-length list2-length) (if (sublist list1 list2) :sublist :unequal)

      :else (if (sublist list2 list1) :superlist :unequal))))
