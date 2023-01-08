(ns sublist)

(defn appears-at-beginning?
  "Does list1 appear as a prefix of list2?"
  [list1 list2]
  (cond
    (empty? list1) true
    (empty? list2) false
    (not= (first list1) (first list2)) false
    :else (appears-at-beginning? (rest list1) (rest list2))))

(defn sublist?
  "Does list1 occur somewhere in list2?"
  [list1 list2]
  (cond
    (empty? list1) true
    (empty? list2) false
    :else (or (appears-at-beginning? list1 list2)
              (sublist? list1 (rest list2)))))

(defn classify
  "Is list1 a :sublist of, a :superlist of, :equal to, or :unequal to list2?"
  [list1 list2]
  (let [sub (sublist? list1 list2)
        super (sublist? list2 list1)]
    (cond
      (and sub super) :equal
      sub :sublist
      super :superlist
      :else :unequal)))