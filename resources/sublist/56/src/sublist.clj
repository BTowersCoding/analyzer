(ns sublist)

(defn serialize [list] (String/join "_" (map #(String/valueOf %) list)))

(defn sublist? [list1 list2]
  (let [a (serialize list1)
        b (serialize list2)]
    (-> b (.contains a))))

(defn classify [list1 list2]                                ;; <- arglist goes here
  (let [list1-sublist-of-list2? (sublist? list1 list2)
        list2-sublist-of-list1? (sublist? list2 list1)
        ]
    (cond
      (and list1-sublist-of-list2? list2-sublist-of-list1?) :equal
      list1-sublist-of-list2? :sublist
      list2-sublist-of-list1? :superlist
      :else :unequal
      )))