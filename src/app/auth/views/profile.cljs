(ns app.auth.views.profile
  (:require [app.components.page-nav :refer [page-nav]]
            [app.components.form :refer [form-group]]
            [app.components.core :refer [button]]
            [reagent.core :as r]
            [re-frame.core :as rf]))

(defn profile
  []
  (let [{:keys [first-name last-name]} @(rf/subscribe [:active-user-profile])
        values (r/atom {:first-name first-name :last-name last-name})]
    (fn []
      [:<>
       [page-nav {:center "Profile"
                  :right  [button {:variant  :secondary
                                   :on-click #(rf/dispatch [:log-out])}
                           "Log out"]}]
       [:div {:class "flex flex-row justify-center"}
        [:div.flex.flex-col.w-full.items-center
         [:div {:class "bg-white rounded p-10 w-1/3"}
          [:h2.py-8.font-bold "Personal Info"]
          [form-group {:id     :first-name
                       :label  "First Name"
                       :type   "text"
                       :values values
                       :class  "my-2"}]
          [form-group {:id     :last-name
                       :label  "Last Name"
                       :type   "text"
                       :values values
                       :class  "my-2"}]
          [button {:on-click #(rf/dispatch [:update-profile @values])
                   :class    "float-right"} "Save"]]
         [:div {:class "bg-white rounded p-2 px-10 w-1/3 mt-4"}
          [:h2.font-bold.py-4 "Danger Zone"]
          [button {:on-click #(when (js/confirm "This will delete your account")
                                (rf/dispatch [:delete-account]))
                   :variant  :danger
                   :class    "w-1/3 mb-4"} "Delete account"]]]]])))
