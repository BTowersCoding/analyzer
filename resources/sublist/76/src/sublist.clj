(ns sublist)

(defn are-equal? [list1 list2]
  (= list1 list2))

(defn sublist? [list1 list2]
  (let [max-index (- (count list2) (count list1))
        length (count list1)]
    (or false
        (not ( nil?
             (loop [index 0]
               (let [match? (= list1 (take length (drop index list2)))
                     max-index-reached? (< index max-index)]
                 (if match?
                   true 
                   (if max-index-reached?
                     (recur (inc index)))))))))))

(comment
;; test exit condition
  (def list1 [1 2 3 5])
  (def list2 [1 2 3 4 5])
  (sublist? list1 list2)
  (= list1 (take 3 (drop 0 list2)))
  )

(defn classify [list1 list2]
  (cond (are-equal? list1 list2) :equal
        (sublist? list1 list2) :sublist
        (sublist? list2 list1) :superlist
        :else :unequal))