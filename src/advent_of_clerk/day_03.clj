;; # 🎄 Advent of Clerk: Day 3
(ns advent-of-clerk.day-03
  (:require [nextjournal.clerk :as clerk]
            [clojure.java.io :as io]
            [clojure.string :as str]))

(def base-path "./inputs/day3")

(clerk/md (slurp (str base-path "/text.md")))

(def input
  (->> (io/reader (str base-path "/input.txt"))
       line-seq
       (map #(str/split % #"\s"))))

;; # Solution
