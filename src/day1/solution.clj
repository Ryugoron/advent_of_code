(ns day1.solution
  (:require [clojure.string :refer [split-lines]])
  (:gen-class))

(defn solve
  [freqs & args]
  (reduce + (map read-string (split-lines (slurp freqs)))))

