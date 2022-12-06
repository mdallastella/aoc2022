;; # 🎄 Advent of Clerk: Day 2
(ns advent-of-clerk.day-02
  (:require [nextjournal.clerk :as clerk]
            [clojure.java.io :as io]
            [clojure.string :as str]))

(def base-path "./inputs/day2")

(clerk/md (slurp (str base-path "/text.md")))

(def input
  (->> (io/reader (str base-path "/input.txt"))
       line-seq
       (map #(str/split % #"\s"))))

;; # Solution
(def ->move-name
  {"A" :rock
   "B" :paper
   "C" :scissors
   "X" :rock
   "Y" :paper
   "Z" :scissors})

(def input-part1
  (map (fn [[x y]] [(->move-name x) (->move-name y)]) input))

;; ## Define play results
(defn play-result
  [play]
  (cond
    (= (first play) (second play))                                3
    (#{[:rock :paper] [:paper :scissors] [:scissors :rock]} play) 6
    :else                                                         0))

;; ## Define play weight
(def move-score
  {:rock 1 :paper 2 :scissors 3})

;; ## Parse strategy guide
(reduce (fn [acc play]
          (+ acc (play-result play) (move-score (second play))))
        0
        input-part1)

;; # Solution 2
(def ->move-result
  {"X" :lose
   "Y" :draw
   "Z" :win})

(def input-part2
  (map (fn [[x y]] [(->move-name x) (->move-result y)]) input))

(defn ->actual-play
  [play]
  (let [[opponent me] play]
    (if (= :draw me)
      [opponent opponent]
      [opponent (get-in {:rock {:win :paper :lose :scissors}
                         :paper {:win :scissors :lose :rock}
                         :scissors {:win :rock :lose :paper}} play)])))

(reduce (fn [acc play]
          (let [actual-play (->actual-play play)]
            (+ acc
               (play-result actual-play)
               (move-score (second actual-play)))))
        0
        input-part2)
