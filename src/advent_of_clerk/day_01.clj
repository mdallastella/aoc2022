;; # 🎄 Advent of Clerk: Day 1
(ns advent-of-clerk.day-01
  (:require [nextjournal.clerk :as clerk]
            [clojure.java.io :as io]))

^{::clerk/visibility {:code :hide :result :hide}}
(def base-path "./inputs/day1")

^{::clerk/visibility {:code :hide :result :hide}}
(def text (slurp (str base-path "/text.md")))

^{::clerk/visibility {:code :hide}}
(clerk/md text)

;; ## Input
(def input
  (line-seq (io/reader (str base-path "/input.txt"))))

;; ## Solution - part 1
;; Split the list of values by empty line and calculate the sum
(def calories-by-elf
  (->> input
       (map parse-long)
       (partition-by nil?)
       (map #(reduce + %))
       (remove nil?)))

;; Find the highest value
(apply max calories-by-elf)

;; ## Solution - part 2
;; Sort the calories from largest to smaller, take the first three and sum them.
(->> calories-by-elf
     (sort >)
     (take 3)
     (reduce +))
