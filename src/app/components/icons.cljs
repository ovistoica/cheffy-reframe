(ns app.components.icons
  (:require
    ["@heroicons/react/solid" :rename {ClockIcon ClockIconFilled
                                       HeartIcon HeartIconFilled}
     :refer [PlusIcon]]

    ["@heroicons/react/outline" :refer [HeartIcon]]
    [reagent.core :as r]))

(defn left-arrow []
  [:svg {:xmlns     "http://www.w3.org/2000/svg"
         :className "h-6 w-6"
         :fill      "none"
         :viewBox   "0 0 24 24"
         :stroke    "currentColor"}
   [:path {:strokeLinecap  "round"
           :strokeLinejoin "round"
           :strokeWidth    "{2}"
           :d              "M10 19l-7-7m0 0l7-7m-7 7h18"}]])


(def heart-solid (r/adapt-react-class HeartIconFilled))
(def heart-outline (r/adapt-react-class HeartIcon))

(def clock (r/adapt-react-class ClockIconFilled))

(def plus (r/adapt-react-class PlusIcon))
