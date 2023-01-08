(ns sublist)


(defn first-element [element data] ;; <- arglist goes here
  ;; your code goes here
  (first(first (filter (fn [x] (= element (last x))) (map list (range (count data)) data)))))

(def first-element-default (fnil first-element 0))

(defn clean-list [data]
  (map int (apply hash-set data)))


(defn compare-lists [list1 list2]
  (every? true? (map (fn [x] (= (first x) (last x))) (map list list1 (drop (first-element-default (first list1) list2) list2)))))

(defn classify2 [list1 list2] ;; <- arglist goes here
  (let [lista (clean-list list1)
        listb (clean-list list2)]
    (let [cmp2 (compare-lists lista listb)
        size (- (count lista) (count listb))]
         (cond
           (or (= (true? cmp2) (< size 0)) (and (= 0 (count lista)) (> 0(count listb)))) :sublist
           (or (= (true? cmp2) (> size 0)) (and (> 0 (count lista)) (= 0(count listb)))) :superlist
           (or (= (true? cmp2) (= size 0)) (and (= 0 (count lista) (count listb)))) :equal
           (= (false? cmp2)) :unequal)
           )))


(defn to-hash-set [data]
  (apply hash-set data))

(defn classify3 [list1 list2] ;; <- arglist goes here
  (cond
    (= list1 list2) :equal
    (if  (> (count list2) (count list1)) (= (every?  list2 list1) true) false) :sublist
    (if  (> (count list1) (count list2)) (= (every?  list1 list2) true) false) :superlist
    :default :unequal)
  )

(defn classify [list1 list2] ;; <- arglist goes here
  (cond
    (= list1 list2) :equal
    (clojure.set/subset?  list1 list2) :sublist
    (clojure.set/superset?  list1 list2) :superlist
    :default :unequal)
  )

