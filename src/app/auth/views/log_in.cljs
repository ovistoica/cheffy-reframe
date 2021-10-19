(ns app.auth.views.log-in
  (:require [app.components.page-nav :refer [page-nav]]))

(defn log-in
  []
  [:div.container.mx-auto
   [page-nav {:center "Log In"}]
   [:form.bg-white.rounded.px-8.pt-6.pb-8.mb-4
    [:div.mb-4
     [:label.block.text-gray-700.text-sm.font-bold.mb-2 {:for "email"} "Email"]
     [:input
      {:id          "email"
       :type        "email"
       :placeholder "john@example.com"
       :class       "shadow appearance-none border rounded-full w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"}]]
    [:div.mb-6
     [:label.block.text-gray-700.text-sm.font-bold.mb-2 {:for "password"} "Password"]
     [:input
      {
       :id          "password"
       :type        "password"
       :placeholder "******************"
       :class       "shadow appearance-none border rounded-full w-full py-2 px-3 text-gray-700 mb-3 leading-tight focus:outline-none focus:shadow-outline"}]
     [:p.text-xs.italic "Please choose a password."]]
    [:div.flex.items-center.justify-between
     [:button {:type  "button"
               :class "bg-green-500 hover:bg-green-700 text-white font-bold py-2 px-4 rounded-full focus:outline-none focus:shadow-outline transition-all"}
      "Sign In"]
     [:a.inline-block.align-baseline.font-bold.text-sm.text-green-500.hover:text-green-800.transition {:href "#"} "Forgot Password?"]]]])
