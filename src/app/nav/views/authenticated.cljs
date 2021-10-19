(ns app.nav.views.authenticated
  (:require
    [app.nav.views.nav-item :refer [nav-item]]
    [re-frame.core :as rf]))

(def nav-items [{:id       :saved
                 :name     "Saved"
                 :href     "#saved"
                 :dispatch #(rf/dispatch [:set-active-nav :saved])}
                {:id       :recipes
                 :name     "Recipes"
                 :href     "#recipes"
                 :dispatch #(rf/dispatch [:set-active-nav :recipes])}
                {:id       :inboxes
                 :name     "Inbox"
                 :href     "#inbox"
                 :dispatch #(rf/dispatch [:set-active-nav :inbox])}
                {:id       :become-a-chef
                 :name     "Chef"
                 :href     "#become-a-chef"
                 :dispatch #(rf/dispatch [:set-active-nav :become-a-chef])}
                {:id       :profile
                 :name     "Profile"
                 :href     "#profile"
                 :dispatch #(rf/dispatch [:set-active-nav :profile])}])

(defn authenticated
  []
  (let [active-nav @(rf/subscribe [:active-nav])]
    [:div {:class "flex justify-end py-1"}
     (for [item nav-items]
       ^{:key (:id item)}
       [nav-item (assoc item :active-nav active-nav)])]))



