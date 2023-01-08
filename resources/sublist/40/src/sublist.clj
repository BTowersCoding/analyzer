(ns sublist)

(defn matched? [list1 list2]
  (when (seq list2)
    (let [cnt1 (count list1)]
         (->> (map = list1 (take cnt1 list2))
              (every? identity)))))

(defn sub-matched? [list1 list2]
  (let [match (matched? list1 list2)]
    (cond
      match true
      (empty? list2) false
      :else (sub-matched? list1 (rest list2)))))

(defn find-matched [list1 list2]
   (cond
     (= list1 list2)
     :equal
     
     (and (sub-matched? list1 list2) (< (count list1) (count list2)))
     :sublist

     (and (sub-matched? list2 list1) (< (count list2) (count list1)))
     :superlist

     :else
     :unequal))

(defn classify [list1 list2] ;; <- arglist goes here
      ;; your code goes here
    (find-matched list1 list2))
