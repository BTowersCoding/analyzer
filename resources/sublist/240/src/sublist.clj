(ns sublist)

(defn aa [l1 l2]
  (cond
    (> (count l1) (count l2)) false
    (= l1 (take (count l1) l2)) true
    :else (aa l1 (rest l2))
    )
  )

(defn classify [list1 list2] ;; <- arglist goes here
      ;; (distinct (into list1 list2))
  (cond
    (= list1 list2) :equal
    (aa list1 list2) :sublist
    (aa list2 list1) :superlist
    :else :unequal))

