(ns sublist)

(defn sublists [seq length]
  (loop [sublist seq result []]
    (if (empty? sublist)
      result
      (recur (drop 1 sublist) (conj result (take length sublist))))))

(defn included?
  [sub super]
  (= (some #(= % sub) (sublists super (count sub))) true))

(defn classify [list1 list2]
  (cond
    (and (included? list1 list2) (included? list2 list1)) :equal
    (included? list1 list2) :sublist
    (included? list2 list1) :superlist
    (and (empty? list1) (empty? list2)) :equal
    :else :unequal))