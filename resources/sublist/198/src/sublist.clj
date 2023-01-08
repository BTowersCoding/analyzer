(ns sublist)

(defn- rest-v [col]
  (into [] (rest col)))

(defn- agregate [smallest largest acc item]
  (if (= smallest acc)
    (reduced acc)
    (let [current-largest (nth largest item)]
      (if (< (count acc) (count smallest))
        (conj acc current-largest)
        (-> acc rest-v (conj current-largest))))))

(defn- contained? [smallest largest]
  (let [match (reduce (partial agregate smallest largest) [] (range 0 (count largest)))]
    (= match smallest)))

(defn- classify-size [list1 list2]
  (let [l1 (count list1)
        l2 (count list2)]
    (cond
      (= l1 l2) [list1 list2 :equal]
      (> l1 l2) [list2 list1 :superlist]
      :else [list1 list2 :sublist])))

(defn classify [list1 list2] ;; <- arglist goes here
  (let [[smallest largest candidate] (classify-size list1 list2)]
    (if (contained? smallest largest)
      candidate
      :unequal)))
