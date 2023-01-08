(ns sublist)

(defn sub? 
  ([sub super] 
   (->> 
     (map-indexed vector super)
     (filter #(= (second %) (first sub)))
     (map #(first %))
     (filter #(sub? sub super %))
     (empty?)
     (not)))
  ([sub super idx]
   (let [last-idx-of-sub (+ idx (count sub))]
     (if (> last-idx-of-sub (count super))
     false
     (= (subvec super idx last-idx-of-sub) sub)))))

(defn classify [list1 list2] ;; <- arglist goes here
      ;; your code goes here
  (cond
    (= list1 list2) :equal
    (= list1 []) :sublist
    (= list2 []) :superlist
    (sub? list1 list2) :sublist
    (sub? list2 list1) :superlist
    :else :unequal))