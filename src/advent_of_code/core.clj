(ns advent-of-code.core
  (require [solutions.solution :as solution]
           [solutions.guards :as guards])
  (:gen-class))

(def funs {"day1" solution/day1
           "day2" solution/day2
           "day3" solution/day3
           "day4" guards/day4})

(defn help [& args]
  (println "Welcome to Advent of Code 2018.\nUse one of the following commands/days to start:")
  (clojure.string/join ", " (keys funs))
)

(defn -main
  "I don't do a whole lot ... yet."
  ([]
   (println (help)))
  ([fun & args]
   (println (apply (or (funs fun) help) args))))
