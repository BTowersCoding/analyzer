(ns sublist)

(defn includes?
  [list1 list2]
  (let [list1-str (clojure.string/replace (str list1) #"\(|\)|\[|\]"  "")]
      (clojure.string/includes? (str list2) list1-str)))

(defn classify [list1 list2] ;; <- arglist goes here
  (cond
    (= list1 list2)         :equal
    (includes? list1 list2) :sublist
    (includes? list2 list1) :superlist
    :else                   :unequal))
