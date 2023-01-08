(ns sublist)

(defn sublist-no-drop [list1 list2]
  (cond
    (empty? list1) true
    (empty? list2) false
    :else
    (let [[h1 & r1] list1
          [h2 & r2] list2]
      (if (= h1 h2)
        (sublist-no-drop r1 r2)
        false))))

(defn sublist [list1 list2]
  (cond
    (empty? list1) true
    (empty? list2) false
    :else
    (let [[h1 & r1] list1
          [h2 & r2] list2]
      (if (= h1 h2)
        (or (sublist-no-drop r1 r2)
            (sublist list1 r2))
        (sublist list1 r2)))))

(defn superlist [list1 list2]
  (sublist list2 list1))

(defn equal [list1 list2]
  (if (empty? list1)
    (empty? list2)
    (let [[h1 & r1] list1
          [h2 & r2] list2]
      (if (= h1 h2)
        (equal r1 r2)
        false))))

(defn classify [list1 list2] ;; <- arglist goes here
  (cond
    (equal list1 list2) :equal
    (sublist list1 list2) :sublist
    (superlist list1 list2) :superlist
    :else :unequal))

(sublist [1 1 2] [0 1 1 1 2 1 2])
(classify [1 1 2] [0 1 1 1 2 1 2])