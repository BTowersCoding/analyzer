(ns sublist)

(defn- sublist? [lst1 lst2]
  (some?
   ((into #{} (partition (count lst1) 1 lst2)) lst1)))

(defn classify
  "Given two lists determine whether :
    * the first list is contained within the second
    * the second list is contained within the first list
    * both lists are equal
    * none of the above, lists are unequal
   
   Specifically, a list A is a sublist of list B 
   if by dropping 0 or more elements from the front of B and 
   0 or more elements from the back of B 
   you get a list that's completely equal to A."
  
  [lst1 lst2]
  (cond
    (= lst1 lst2) :equal
    (sublist? lst1 lst2) :sublist
    (sublist? lst2 lst1) :superlist
    :else :unequal)

)
