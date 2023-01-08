(ns sublist)

(defn classify [list1 list2]
      (let [len-list1 (count list1)
            len-list2 (count list2)]
        (cond
          (= list1 list2) :equal
          (some #(= list1 %) (partition len-list1 1 list2)) :sublist
          (some #(= list2 %) (partition len-list2 1 list1)) :superlist
          :else :unequal
          )



        )
)
