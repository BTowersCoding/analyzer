(ns sublist)

(defn classify [list1 list2] 
  (defn sublist? [list1 list2]
    ;; Test if list1 is a sublist of list2
    ;; this means if we drop 0 or more elements from front of list2
    ;; and 0 or more elements from the back of B we get something
    ;; equal to A.
    (let [n (count list1)]
      (loop [test-lst list2]
        (cond 
          (= list1 (take n test-lst)) true  ;; if equal, sublist
          (empty? test-lst) false ;; nothing can be sublist of empty (unless empty)
          :else (recur (rest test-lst)))))) ;; drop from head and recur
  (cond
    (= list1 list2) :equal
    (sublist? list1 list2) :sublist
    (sublist? list2 list1) :superlist
    :else :unequal))
    
