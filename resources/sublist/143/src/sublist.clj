(ns sublist)

(defn- equal? [list1 list2]
  (= list1 list2))

(defn- starts?
  "Is list1 starting with the content of list2?"
  [list1 list2]
  (= (take (count list2) list1) list2))

(defn- split-list-with-rest
  "given a list, split and return earch position with a rest.
   Example:
   :input  [1 2 3 4]
   :output [[1 2 3 4] [2 3 4] [3 4] [4] []]"
  [list1]
  (loop [list1 list1
         result [list1]]
    (if (seq list1)
      (recur (rest list1) (into result [(rest list1)]))
      result)))

(defn- inside?
  "Is list1 inside of list2?"
  [list1 list2]
  (->> (split-list-with-rest list2)
       (map #(starts? % list1))
       (some true?)))

(defn sublist?
  "Is list1 a sublist of list2?"
  [list1 list2]
  (let [c-list1 (count list1)
        c-list2 (count list2)]
    (and (< c-list1 c-list2)
         (inside? list1 list2))))

(defn superlist?
  "Is list1 a superlist of list2?"
  [list1 list2]
  (let [c-list1 (count list1)
        c-list2 (count list2)]
    (and (> c-list1 c-list2)
         (inside? list2 list1))))

(defn classify [list1 list2]
  (cond
    (equal? list1 list2) :equal
    (sublist? list1 list2) :sublist
    (superlist? list1 list2) :superlist
    :else :unequal))
