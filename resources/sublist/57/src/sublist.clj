(ns sublist)

(defn sublistOf?
  "Check if list1 is a subset of list2"
  [list1 list2]
  (let [sl1 (-> (str list1)
                (clojure.string/replace "]" "")
                (clojure.string/replace "[" ""))
        sl2 (-> (str list2)
                (clojure.string/replace "]" "")
                (clojure.string/replace "[" ""))]
    (if (> (.indexOf sl2 sl1) -1)
      true false)))

(defn classify 
  [list1 list2] 
  (if (= list1 list2)
    :equal
    (if (sublistOf? list1 list2)
      :sublist
      (if (sublistOf? list2 list1)
        :superlist
        :unequal))))

(comment

  (def A  [1 2 3])
  (def B  [1 2 3 4 5])
  (def C  [2 3 4 5])

  (.indexOf "abc" "b")
  (.indexOf (str A) (str B))

  (def sA (-> (str A)
              (clojure.string/replace "]" "")
              (clojure.string/replace "[" "")))
  (def sB (-> (str B)
              (clojure.string/replace "]" "")
              (clojure.string/replace "[" "")))
  (def sC (-> (str C)
              (clojure.string/replace "]" "")
              (clojure.string/replace "[" "")))

  (print sA)
  (print sB)
  (print sC)

  (.indexOf sA sB)
  (.indexOf sB sA)
  (.indexOf sB sC)
  (.indexOf sC sB)
  (.indexOf sA sC)

  (sublistOf? sA sB)
  (sublistOf? sB sA)
  (sublistOf? sB sC)
  (sublistOf? sC sB)
  (sublistOf? sA sC)

  (classify sA sB)
  (classify sB sA)
  (classify sB sC)
  (classify sC sB)
  (classify sA sC)

  (-> (str A)
      (clojure.string/replace "]" "")
      (clojure.string/replace "[" ""))

  (subs (str A) 1)
  (str (conj A ))

  (= A B)
  (= B C)

  (for [a A
        b B]
    (if (= a b)
      [a b]) )

)

