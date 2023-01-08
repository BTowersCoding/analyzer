(ns sublist)

(defn front-equals? [small big]
  (if (empty? small)
    true
    (if (= (first small) (first big))
      (front-equals? (rest small) (rest big))
      false)))

(defn sublist? [small big]
  (if (empty? small)
    true
    (if (empty? big)
      false
      (if (front-equals? small big)
        true
        (sublist? small (rest big))))))

(defn classify [list1 list2]
  (case (compare (count list1) (count list2))
    0  (if (= list1 list2)        :equal     :unequal)
    -1 (if (sublist? list1 list2) :sublist   :unequal)
    1  (if (sublist? list2 list1) :superlist :unequal)
    ))
