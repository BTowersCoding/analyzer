(ns sublist)

(defn needle-prefix-in-haystack
  [needle haystack]
  (or
    (= needle (take (count needle) haystack))
    (and
      (> (count haystack) 0)
      (needle-prefix-in-haystack needle (rest haystack))
    )
  )
)

(defn classify [list1 list2] ;; <- arglist goes here
  (if
    (= list1 list2)
    :equal
    (if
      (needle-prefix-in-haystack list1 list2)
      :sublist
      (if
        (needle-prefix-in-haystack list2 list1)
        :superlist
        :unequal
      )
    )
  ) 
)
