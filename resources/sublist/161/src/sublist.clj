(ns sublist)


(defn sublist [s l]
  (let [to-take (count s)
        to-drop (- (count l) to-take)]
    (some (fn [n] (= s (take to-take (drop n l)))) (range 0 (inc to-drop)))))

(defn classify [list1 list2] ;; <- arglist goes here
      (let [l1 (count list1) l2 (count list2)]
        (cond 
          (= l1 l2) (if (= list1 list2) :equal :unequal)
          (< l1 l2) (if (sublist list1 list2) :sublist :unequal)
          :else      (if (sublist list2 list1) :superlist :unequal)
          )))
