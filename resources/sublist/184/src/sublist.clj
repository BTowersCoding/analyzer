(ns sublist)

(defn sameSize? [list1 list2]
  (= (count list1) (count list2)))

(defn isSublist [littleList bigList]
  (if (sameSize? littleList bigList)
    (if (= littleList bigList) :equal :unequal)
    (if (= littleList (take (count littleList) bigList)) :equal (isSublist littleList (drop 1 bigList)))))

(defn classify [list1 list2] ;; <- arglist goes here
  (cond (< (count list1) (count list2)) (if (= (isSublist list1 list2) :equal) :sublist :unequal)
        (> (count list1) (count list2)) (if (= (isSublist list2 list1) :equal) :superlist :unequal)
        :else (if (= list1 list2) :equal :unequal)))