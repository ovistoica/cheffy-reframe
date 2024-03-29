(ns app.helpers
  (:require [cljs-time.core :as t]
            [clojure.string :as string]
            [goog.string :as gstring]))

;; https://stackoverflow.com/questions/32511405/how-would-time-ago-function-implementation-look-like-in-clojure
(defn time-ago
  [timestamp]
  (let [units [{:name "second" :limit 60 :in-second 1}
               {:name "minute" :limit 3600 :in-second 60}
               {:name "hour" :limit 86400 :in-second 3600}
               {:name "day" :limit 604800 :in-second 86400}
               {:name "week" :limit 2629743 :in-second 604800}
               {:name "month" :limit 31556926 :in-second 2629743}
               {:name "year" :limit js/Number.MAX_VALUE :in-second 31556926}]
        time (js/Date. timestamp)
        diff (t/in-seconds (t/interval time (t/now)))]
    (if (< diff 5)
      "just now"
      (let [unit (first (drop-while #(or (>= diff (:limit %))
                                         (not (:limit %)))
                                    units))]
        (-> (/ diff (:in-second unit))
            Math/floor
            int
            (#(str % " " (:name unit) (when (> % 1) "s") " ago")))))))


;; Local storage

(defn set-item-ls!
  [key item]
  (.setItem js/localStorage key (str item)))

(defn remove-item-ls!
  [key]
  (.removeItem js/localStorage key))

(defn get-item-ls
  [key]
  (.getItem js/localStorage key))

;; Tailwind helpers
(defn classes
  "Join multiple classes into a single string.

  Also filters falsy parameters"
  [& classes]
  (string/join " " (filter string? classes)))


(defn unescape-html
  [entities]
  (gstring/unescapeEntities entities))

