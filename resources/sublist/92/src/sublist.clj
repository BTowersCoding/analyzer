(ns sublist)

(defn compare-two-lists-with-same-head [list1 list2]
  (let [first-list1 (first list1)
        first-list2 (first list2)]
    (cond
      (nil? first-list1) true
      (nil? first-list2) false
      (not= first-list1 first-list2) false
      (= first-list1 first-list2) (recur (rest list1) (rest list2)))))

(defn is-sublist [list1 list2]
  (let [first-list1 (first list1)
        first-list2 (first list2)]
    (cond 
      (and (= first-list1 first-list2)(compare-two-lists-with-same-head list1 list2)) true
      (nil? first-list2) false
      :else (recur list1 (rest list2)))))

(defn classify [list1 list2] ;; <- arglist goes here
      ;; your code goes here
  (let [list1-sublist-list2 (is-sublist list1 list2)
        list2-sublist-list1 (is-sublist list2 list1)]
    (cond
      (and list1-sublist-list2 list2-sublist-list1) :equal 
      list1-sublist-list2 :sublist 
      list2-sublist-list1 :superlist
      :else :unequal))
  )
