(ns advent-of-code.core
  (require [day1.solution :as day1])
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  ([]
   (println "Please provide the name of a day and the correspoding arguments"))
  ([day & args]
   (if (= day "day1") (day1/solve args))))
