(ns sublist)

(defn list-match-head [comparison remainder]
  (cond
    (empty? comparison) true
    (empty? remainder) false
    :else (and (= (first comparison) (first remainder))
               (list-match-head (rest comparison) (rest remainder)))))

(defn is-sublist [comparison remainder]
  (cond
    (empty? remainder) false
    :else (or (list-match-head comparison remainder)
              (is-sublist comparison (rest remainder)))))

;; (is-sublist [1 2 3] [1 2 3 4 5])
;; (is-sublist [3 4 5] [1 2 3 4 5])
;; (is-sublist [3 4] [1 2 3 4 5])

(defn is-equal [list1 list2]
  (cond
    (and (empty? list1) (empty? list2)) true
    (or (empty? list1) (empty? list2)) false
    (= (first list1) (first list2)) (is-equal (rest list1) (rest list2))
    :else false))
          
;; (is-equal [1 2 3] [1 2 3])

(def is-superlist #(is-sublist %2 %1))

;; (is-superlist [1 2 3 4 5] [2 3 4])

(defn classify [list1 list2]
  (cond
    (is-equal list1 list2) :equal
    (is-sublist list1 list2) :sublist
    (is-superlist list1 list2) :superlist
    :else :unequal))
