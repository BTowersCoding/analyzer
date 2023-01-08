(ns sublist)

(defn classify [list1 list2] ;; <- arglist goes here
  (if (= list1 list2) :equal
      (do
        (def longest-list (if (>= (count list1) (count list2)) list1 list2))
        (def shortest-list (if (< (count list1) (count list2)) list1 list2))
        (def longest-list-size (count longest-list))
        (def shortest-list-size (count shortest-list))
        (def size-diff (- longest-list-size shortest-list-size))
        (def lists-match
          (loop [ index 0 match false]
            (def new-index (inc index))
            (def current-sublist (take shortest-list-size (drop index longest-list)))
            (def new-match (or match (= current-sublist shortest-list)))
            ; (println "new-match" new-match "current-sublist" current-sublist "shortest-list" shortest-list)
            (if (and (< (+ index shortest-list-size) longest-list-size) (= match false))
              (recur new-index new-match)
              new-match)))
        (cond
          (not lists-match) :unequal
          (= list1 longest-list) :superlist
          (= list2 longest-list) :sublist)
        )))
