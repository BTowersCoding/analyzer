(ns sublist)

(defn classify [list1 list2]
    (def list1_string (clojure.string/join " " list1))
    (def list2_string (clojure.string/join " " list2))
    (cond
        (= list1_string list2_string) "equal"
        (clojure.string/includes? list2_string list1_string) "subset"
        (clojure.string/includes? list1_string list2_string) "superset"
        :else "unequal"
        )
)
