(ns sublist)

(defn sub?
  [a b ca cb]
  (some #(= a (take ca (drop % b)))
        (range 0 (- (inc cb) ca))))

(defn classify [a b]
  (let [x (count a)
        y (count b)]
    (cond
      (= x y) (if (= a b) :equal :unequal)
      (< x y) (if (sub? a b x y) :sublist :unequal)
      (> x y) (if (sub? b a y x) :superlist :unequal))))
