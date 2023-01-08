(ns sublist)

(defn is_sublist?
  "check if l1 is a sublist of l2
   l1 = [3 4] is a sublist of l2 = [1 2 3 4 5]
   "
  [l1 l2]
  (let [c1 (count l1)]
    (loop [l l2]
      (let [lcomp (take c1 l)]
        (cond
          (> c1 (count lcomp)) false
          (= l1 lcomp) true
          :else (recur (drop 1 l)))))))

(defn classify [l1 l2] ;; <- arglist goes here
   (cond
     (= l1 l2) :equal
     (is_sublist? l1 l2) :sublist
     (is_sublist? l2 l1) :superlist
     :else :unequal))
