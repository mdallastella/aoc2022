(ns user
  (:require [nextjournal.clerk :as clerk]
            [advent-of-clerk.index :as index]
            [clojure.java.io :as io]
            [clj-http.client :as http])
  (:import [java.time LocalDate]))

(def session
  (System/getenv "AOC22_SESSION"))

(defn prepare-skel-day
  [day]
  (let [path (io/as-file (str "./inputs/day" day))]
    (when-not (.exists path)
      (.mkdirs path))
    (str path)))

(defn download-input-day
  ([]
   (let [today (-> (LocalDate/now) .getDayOfMonth)]
     (download-input-day today)))
  ([day]
   (let [path    (prepare-skel-day day)
         aoc-url (str "https://adventofcode.com/2022/day/" day "/input")]
     (->> (http/get aoc-url
                    {:headers {"Cookie" (str "session=" session)}})
          :body
          (spit (str path "/input.txt"))))))

(clerk/serve! {:port 7878 :browse true})

(comment
  (clerk/build! {:paths (index/build-paths) :browse true}))
