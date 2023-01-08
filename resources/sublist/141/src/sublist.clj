(ns sublist)


(defn classify [list1 list2] 
  (let [list1s (clojure.string/join "." list1)
       list2s (clojure.string/join "." list2)]
       (cond (= list1 list2) :equal
             (clojure.string/includes? list1s list2s) :superlist
             (clojure.string/includes? list2s list1s) :sublist
             :else :unequal) 
        
        )  
)

