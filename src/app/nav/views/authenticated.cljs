(ns app.nav.views.authenticated
  (:require
    [app.nav.views.nav-item :refer [nav-item]]
    [re-frame.core :as rf]
    [app.router :as router]))

(def nav-items [{:id       :saved
                 :name     "Saved"
                 :href     (router/path-for :saved)
                 :dispatch #(rf/dispatch [:set-active-page :saved])}
                {:id       :recipes
                 :name     "Recipes"
                 :href     (router/path-for :recipes)
                 :dispatch #(rf/dispatch [:set-active-page :recipes])}
                {:id       :inboxes
                 :name     "Inbox"
                 :href     (router/path-for :inboxes)
                 :dispatch #(rf/dispatch [:set-active-page :inboxes])}
                {:id       :become-a-chef
                 :name     "Chef"
                 :href     (router/path-for :become-a-chef)
                 :dispatch #(rf/dispatch [:set-active-page :become-a-chef])}
                {:id       :profile
                 :name     "Profile"
                 :href     (router/path-for :profile)
                 :dispatch #(rf/dispatch [:set-active-page :profile])}])

(defn authenticated
  []
  (let [active-page @(rf/subscribe [:active-page])]
    [:div {:class "flex justify-end py-1"}
     (for [item nav-items]
       ^{:key (:id item)}
       [nav-item (assoc item :active-page active-page)])]))



