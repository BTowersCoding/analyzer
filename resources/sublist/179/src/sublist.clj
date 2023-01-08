(ns sublist)

(defn classify [list1 list2]
  (let [prefix? (fn [a b]
                  (cond
                    (empty? a) true
                    (empty? b) false
                    :else (and (= (first a) (first b))
                               (recur (rest a) (rest b)))))
        ; with length information, one can optimize more...
        includes? (fn [a b]
                    (cond
                      (prefix? a b) true
                      (empty? b) false
                      :else (recur a (rest b))))
        length-1 (count list1)
        length-2 (count list2)]
    (cond
      (< length-1 length-2) (if (includes? list1 list2)
                              :sublist
                              :unequal)
      (> length-1 length-2) (if (includes? list2 list1)
                              :superlist
                              :unequal)
      :else (if (= list1 list2)
              :equal
              :unequal))))
