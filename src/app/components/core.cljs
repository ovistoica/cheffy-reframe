(ns app.components.core
  (:require [reagent.core :as r]
            [app.helpers :as h]))

(defn button
  [{:keys [on-click variant class]} & children]
  [:button {:type     "button"
            :on-click on-click
            :class    (h/classes "bg-green-500 hover:bg-green-600 text-white"
                                 (when (= variant :secondary)
                                   "bg-gray-200 text-gray-600 hover:bg-gray-300")
                                 (when (= variant :danger)
                                   "bg-red-400 hover:bg-red-600")
                                 "font-bold py-2 px-4 rounded-full"
                                 "focus:outline-none focus:shadow-outline transition-all"
                                 class)}
   children])

(defn button-link
  [{:keys [href on-click class variant]} & children]
  [:a
   {:class    (h/classes "inline-block align-baseline font-bold text-sm text-green-500"
                         "text-base py-2 px-2 hover:text-green-800 transition"
                         (when (= variant :secondary) "text-gray-500 hover:text-gray-800")
                         class)
    :href     (or href "#")
    :on-click on-click} children])




;[:button {:type  "button"
;          :class (classes "w-full inline-flex justify-center rounded-md border border-transparent shadow-sm"
;                          "px-4 py-2 bg-green-600 text-base font-medium text-white hover:bg-green-700"
;                          "focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-green-500 sm:ml-3"
;                          "sm:w-auto sm:text-sm")} "Delete"]
;[:button {:type  "button"
;          :class (classes "mt-3 w-full inline-flex justify-center rounded-md border border-gray-300 shadow-sm"
;                          "px-4 py-2 bg-white text-base font-medium text-gray-700 hover:bg-gray-50"
;                          "focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500 sm:mt-0"
;                          "sm:ml-3 sm:w-auto sm:text-sm")} "Save"]
