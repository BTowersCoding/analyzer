(ns sublist)

(use '[clojure.string :as s])

(defn classify [list1 list2]

  (def list1s (reduce str (interpose ", " list1)))
  (def list2s (reduce str (interpose ", " list2)))

  (if (= list1s list2s) 
    :equal 
    (if (or (= list1s "") (s/includes? list2s list1s))
      :sublist 
      (if (or (= list2s "") (s/includes? list1s list2s))
        :superlist 
        :unequal)))
)
