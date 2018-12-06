(ns solutions.guards
  (:require [clojure.string :refer [split-lines includes?]]
            [java-time :as t]
            )
  (:gen-class))

(def time-format "yyyy-MM-dd HH:mm")

(defn prep-time 
  "Reads the initial time information"
  [time-line]
  (let
      [pattern (re-find #"\[(\d+-\d+-\d+ \d+:(\d+))\](.*)" time-line)
       [_ date-time time action] pattern
       parse-time (t/local-date-time time-format date-time)
       ]
    {:s parse-time :t time :a action}
    ))
  
(defn guard
  "Tests the action, if it is a guard"
  [action]
  (let [patterns (re-find #"Guard #(\d+) begins shift" action)]
    (if (> (count patterns) 1)
      (nth patterns 1))))

(defn prep-action
  [acc el]
  (let [g (guard (el :a))
        res [(Integer/parseInt (el :t))              
             (or g (nth acc 1))
             (nil? g)]]
    res))
  

(defn prepare-times [times-file] 
  (let 
      [times (split-lines (slurp times-file))
       extract (map prep-time times)
       sorted (sort #(t/before? (%1 :s) (%2 :s)) extract)
       prep (next (reductions prep-action [0 -1 nil] sorted))
       clean (map drop-last (filter #(nth % 2) prep))
       sleep (map (fn [[[s g] [e g]]] [g [s e]]) (partition 2 clean))
       ]
    sleep)
  )

(defn best-time [times]
  24)

(defn best-guard [times]
  (let [lengths (map (fn [[g [s e]]] [g (- e s)]) times)
        per-guard (group-by first lengths)
        guard-time (zipmap (keys per-guard) (map #(reduce + (map second  %)) (vals per-guard)))
        ]
    (read-string (key(apply max-key val guard-time)))
    ))


(defn day4 [times & args]
  (let [stimes (prepare-times times)
        bg (best-guard stimes)
        tpg (map second 
                 (filter #(= bg (read-string (first %))) 
                         stimes))
        ranges (apply concat (map #(range 
                                (first  %1) 
                                (second  %1)) 
                              tpg))
        freqs (frequencies ranges)]
    (* (key (apply max-key val freqs)) bg))
  )

