(ns app.auth.views.log-in
  (:require [app.components.page-nav :refer [page-nav]]
            [app.components.core :refer [button]]
            [app.components.form :refer [form-group]]
            [reagent.core :as r]
            [re-frame.core :as rf]
            [app.router :as router]))


(defn log-in
  []
  (let [initial-values {:email "" :password ""}
        values (r/atom initial-values)]
    (fn []
      [:div.container.mx-auto.max-w-sm.md:max-w-lg
       [page-nav {:center "Log In"}]
       [:form.bg-white.rounded.px-8.pt-6.pb-8.mb-4
        [form-group {:id          :email
                     :label       "Email"
                     :type        "email"
                     :placeholder "john@example.com"
                     :values      values}]
        [form-group {:id          :password
                     :label       "Password"
                     :type        :password
                     :placeholder "*******"
                     :values      values}]
        [:div.flex.items-center.justify-between
         [:button {:type     "button"
                   :on-click #(rf/dispatch [:log-in @values])
                   :class    "bg-green-500 hover:bg-green-700 text-white font-bold py-2 px-4 rounded-full focus:outline-none focus:shadow-outline transition-all"}
          "Sign In"]
         [:a.inline-block.align-baseline.font-bold.text-sm.text-green-500.hover:text-green-800.transition
          {:href     (router/path-for :sign-up)
           :on-click #(rf/dispatch [:set-active-page :sign-up])} "Sign Up"]]]])))
