(ns advent-of-code.core
  (require [day1.solution :only [solve]])
  (:gen-class))

(def funs {"day1" day1.solution/solve})

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
