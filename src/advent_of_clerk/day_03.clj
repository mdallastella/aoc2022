;; # 🎄 Advent of Clerk: Day 3
(ns advent-of-clerk.day-03
  (:require [nextjournal.clerk :as clerk]
            [clojure.java.io :as io]
            [clojure.set :as sets]
            [clojure.string :as str]))

(def base-path "./inputs/day3")

(clerk/md (slurp (str base-path "/text.md")))

(def input
  (->> (io/reader (str base-path "/input.txt"))
       line-seq))

;; # Solution 1
(def char->keyword
  (comp keyword str first))

(defn letters-range
  "Return all the characters from `start` to `end`."
  [start end]
  (->> (range (int start) (inc (int end)))
       (map (comp keyword str char))))

(def item-values
  "Create a map with the values of each item."
  (merge (zipmap (letters-range \a \z) (range 1 27))
         (zipmap (letters-range \A \Z) (range 27 53))))

;; Split the string in two halves, build to sets and find the
;; intersections.
(defn find-misplaced-item
  [x]
  (let [half (/ (count x) 2)]
    (->> (split-at half x)
         (map set)
         (apply sets/intersection)
         char->keyword)))

;; Compute misplaced item priority sum
(->> input
     (map find-misplaced-item)
     (map item-values)
     (reduce +))

;; # Solution 2
;; - Divide the input lines in group of three
;; - Find the common letter between the three
;; - Compute its value
(defn common-letter
  [m]
  (->> (map set m)
       (apply sets/intersection)
       char->keyword))

(->> input
     (partition 3)
     (map common-letter)
     (map item-values)
     (reduce +))
