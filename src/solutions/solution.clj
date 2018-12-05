(ns solutions.solution
  (:require [clojure.string :refer [split-lines]]
            [clojure.set :refer [map-invert]])
  (:gen-class))

(defn day1
  [freqs & args]
  (reduce + 
          (map read-string 
               (split-lines (slurp freqs)))))

(defn freq-analysis
  [line]
  (let [freqs (map-invert (frequencies line))]
    [(if (contains? freqs 2) 1 0) (if (contains? freqs 3) 1 0)]))

(defn day2
  [lines & args]
  (reduce *
          (reduce 
           #(map + %1 %2) 
           (map 
            freq-analysis 
            (split-lines (slurp lines))))))

(defn day3
  [& args]
  "Not implemented")
