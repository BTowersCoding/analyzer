(ns sublist)

(defn sublist? [shorter-list longer-list]
  (let [size (count shorter-list)]
    (some #(= % shorter-list) (partition size 1 longer-list))))

(defn classify [list1 list2]
  (let [length-delta (- (count list1) (count list2))]
    (cond
      (neg? length-delta) (if (sublist? list1 list2) :sublist :unequal)
      (pos? length-delta) (if (sublist? list2 list1) :superlist :unequal)
      :else (if (= list1 list2) :equal :unequal))))