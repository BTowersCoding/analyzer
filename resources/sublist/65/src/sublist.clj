(ns sublist)

(defn list-with-pop [list]
  (if (> (count list))
    (rest (butlast list))))

(defn is-sublist-removing-two-points? [list1 list2]
  (let [first_item (first list2)
        list-pop (list-with-pop list2)]
    (if (not (nil? first_item))
      (if (or (= list1 (rest list2)) (= list1 list-pop))
        true
        (is-sublist-removing-two-points? list1 list-pop)
        ))))

(defn is-sublist-only-pop? [list1 list2]
  (let [first_item (first list2)]
    (if (not (nil? first_item))
      (if (= list1 (butlast list2))
        true
        (is-sublist-only-pop? list1 (butlast list2))
        ))))

(defn is-sublist? [list1 list2]
  (let [first_item (first list2)]
    (if (not (nil? first_item))
      (if (or (= list1 (rest list2)) (= list1 (list-with-pop list2)))
        true
        (is-sublist? list1 (rest list2))
        ))))

(defn is-equal? [list1 list2]
  (= (compare list1 list2) 0)
  )

(defn classify [list1 list2] ;; <- arglist goes here
  (if (is-equal? list1 list2)
    :equal
    (if (or (is-sublist? list1 list2)
            (is-sublist-removing-two-points? list1 list2)
            (is-sublist-only-pop? list1 list2))
      :sublist
      (if (or (is-sublist? list2 list1)
              (is-sublist-removing-two-points? list2 list1)
              (is-sublist-only-pop? list2 list1))
        :superlist
        :unequal
        )
      )
    )
  )
