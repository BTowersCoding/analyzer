(ns sublist)

(defn rmhead [v]
  (subvec v 1))
(defn rmtail [v]
  (pop v))

(defn classify [list1 list2] ;; <- arglist goes here
      ;; your code goes here
  ;(if (= list1 list2) :equal 
  ;  (or (classify ))
  ;
  ;  )
  (println list1 list2 (= list1 list2))
  (let [result (or   (when (= list1 list2)  :equal)
        (when (= 1 ( count list2)) :unequal)
        (when (empty? list1) :sublist)
        (when (empty? list2) :superlist)
        (and (remove #(= :unequal %) (classify list1 (rmhead list2)))
             (remove #(= :unequal %) (classify list1 (rmtail list2))))
        false
        )]
    (println result)    
    result)
)


(comment
(classify [] [])
(classify [] [1 2])
(classify [1 2] [])
(classify [1 2 3] [1 2 3])
(classify [1 2 3] [2 3 4])
(classify [1 2 5] [0 1 2 5 6])
(classify [1 2 5] [0 1 2 3 1 2 5 6])

  )
