(ns solutions.solution
  (:require [clojure.string :refer [split-lines split join]]
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


(defn claim-to-set
  [claim]
  (let [parts (split claim #" ")
        rmcol (join "" (drop-last (nth parts 2)))
        pos (split rmcol #",")
        dim (split (nth parts 3) #"x")
        [a b] (map read-string pos)
        [w h] (map read-string dim)]
    (for [x (range a (+ a w))
            y (range b (+ b h))]
        [x y])))

(defn day3
  [claims & args]
  (let [input (split-lines (slurp claims))
        claim-set (map claim-to-set input)
        freq-claims (frequencies (apply concat claim-set))]
    (count (filter #(> % 1) (vals  freq-claims))))
  )
