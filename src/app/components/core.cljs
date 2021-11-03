(ns app.components.core
  (:require [reagent.core :as r]))

(defn button
  [{:keys [on-click variant class]} & children]
  [:button {:type     "button"
            :on-click on-click
            :class    (str "bg-green-500 hover:bg-green-800 text-white "
                           (when (= variant :secondary)
                             "bg-gray-200 text-gray-600 hover:bg-gray-300 ")
                           (when (= variant :danger)
                             "bg-red-400 hover:bg-red-600")
                           "font-bold py-2 px-4 rounded-full "
                           "focus:outline-none focus:shadow-outline transition-all "
                           class)}
   children])

(defn button-link
  [{:keys [href on-click class]} & children]
  [:a.inline-block.align-baseline.font-bold.text-sm.text-green-500.hover:text-green-800.transition
   {:href "#sign-up"}])


