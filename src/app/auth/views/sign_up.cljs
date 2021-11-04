(ns app.auth.views.sign-up
  (:require [app.components.page-nav :refer [page-nav]]
            [app.components.form :refer [form-group]]
            [reagent.core :as r]
            [re-frame.core :as rf]
            [app.router :as router]))


(defn sign-up
  []
  (let [initial-values {:first-name ""
                        :last-name  ""
                        :email      ""
                        :password   ""}
        values (r/atom initial-values)]
    (fn []
      [:div.container.mx-auto.max-w-sm.md:max-w-lg
       [page-nav {:center "Sign up"}]
       [:form.bg-white.rounded.px-8.pt-6.pb-8.mb-4.w-full
        [form-group {:id          :first-name
                     :label       "First name"
                     :type        "text"
                     :placeholder "John"
                     :values      values}]
        [form-group {:id          :last-name
                     :label       "Last name"
                     :type        "text"
                     :placeholder "Smith"
                     :values      values}]
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
                   :on-click #(rf/dispatch [:sign-up @values])
                   :class    "bg-green-500 hover:bg-green-700 text-white font-bold py-2 px-4 rounded-full focus:outline-none focus:shadow-outline transition-all"}
          "Sign In"]

         [:div.flex.items-center
          [:span.text-gray-500.mr-2 "Already have an account? "]
          [:a.inline-block.align-baseline.font-bold.text-sm.text-green-500.hover:text-green-800.transition
           {:href     (router/path-for :sign-up)
            :on-click #(rf/dispatch [:set-active-page :log-in])} "Sign In"]]]]])))
