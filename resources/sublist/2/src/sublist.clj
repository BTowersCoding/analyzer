(ns sublist)

(defn are-equal? [list1 list2]
  (= list1 list2))

(defn is-sublist? [list1 list2]
  ;; partition the larger list (list2) in blocks with the same size as the smaller list
  ;; and use a step of 1, this results in a collection of overlapping lists.
  ;; (partition n step coll)
  ;; see https://clojuredocs.org/clojure.core/partition
  ;;
  ;; Check with some #{} if the smaller list is a sublist of the larger list
  (some #{list1} (partition (count list1) 1 list2)))

(defn is-superlist?[list1 list2]
  (is-sublist? list2 list1))

(defn classify [list1 list2]
  (cond
    (are-equal? list1 list2) :equal
    (is-sublist? list1 list2) :sublist
    (is-superlist? list1 list2) :superlist
    :else :unequal))
