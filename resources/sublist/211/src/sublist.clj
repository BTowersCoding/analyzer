(ns sublist)

(defn sublist?
  "Takes two lists A and B as input. Checks whether A is a subslist of B."
  [list1 list2]
  (let 
    [n (count list1)]
    (some #(= list1 %) (cons [] (partition n 1 list2)))))

(defn classify
  "Takes two lists A and B as input. Checks whether A is a sublist of B, 
   B is a sublist of A, A and B are equal, or none of the above.
   Returns :sublist, :superlist, :equal, or :unequal accordingly."
  [list1 list2]
  (cond (= list1 list2) :equal
        (sublist? list1 list2) :sublist
        (sublist? list2 list1) :superlist
        :else :unequal))

(classify [] [])
