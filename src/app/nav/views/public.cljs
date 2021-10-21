(ns app.nav.views.public
  (:require [app.nav.views.nav-item :refer [nav-item]]
            [re-frame.core :as rf]))

(def nav-items [
                {:id       :recipes
                 :name     "Recipes"
                 :href     "#recipes"
                 :dispatch #(rf/dispatch [:set-active-nav :recipes])}
                {:id       :become-a-chef
                 :name     "Chef"
                 :href     "#become-a-chef"
                 :dispatch #(rf/dispatch [:set-active-nav :become-a-chef])}
                {:id       :sign-up
                 :name     "Sign up"
                 :href     "#sign-up"
                 :dispatch #(rf/dispatch [:set-active-nav :sign-up])}
                {:id       :log-in
                 :name     "Log In"
                 :href     "#log-in"
                 :dispatch #(rf/dispatch [:set-active-nav :log-in])}])

(defn public
  []
  (let [active-nav @(rf/subscribe [:active-nav])]
    (fn []
      [:div {:class "flex justify-end py-1"}
       (for [item nav-items]
         ^{:key (:id item)}
         [nav-item (assoc item :active-nav active-nav)])])))



