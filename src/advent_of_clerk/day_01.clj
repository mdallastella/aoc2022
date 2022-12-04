;; # 🎄 Advent of Clerk: Day 1
(ns advent-of-clerk.day-01
  (:require [nextjournal.clerk :as clerk]
            [clojure.java.io :as io]))

^{::clerk/visibility {:code :hide :result :hide}}
(def base-path "./inputs/day1")

^{::clerk/visibility {:code :hide :result :hide}}
(def text (slurp (str base-path "/text.md")))

(clerk/md text)

;; ## Input
(def input
  (line-seq (io/reader (str base-path "/input.txt"))))

;; ## Solution
;; Split the list of values by empty line and calculate the sum
(def food-by-elf
  (->> input
       (map parse-long)
       (partition-by nil?)
       (map #(apply + %))))

;; Find the highest value
(apply max (remove nil? food-by-elf))
