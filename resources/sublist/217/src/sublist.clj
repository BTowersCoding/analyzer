(ns sublist)

(defn- check-sublist [list1 list2]
  "partition creates sublists with size of list1 out of list2
   after each list the next sublist is created with the next index"
  (some #(= % list1) (partition (count list1) 1 list2)))

(defn classify [list1 list2]
   (cond
     (= list1 list2) :equal
     (check-sublist list1 list2) :sublist
     (check-sublist list2 list1) :superlist
     :else :unequal))