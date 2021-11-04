(ns app.nav.views.public
  (:require [app.nav.views.nav-item :refer [nav-item]]
            [re-frame.core :as rf]
            [app.router :as router]))

(def nav-items [
                {:id       :recipes
                 :name     "Recipes"
                 :href     (router/path-for :recipes)
                 :dispatch #(rf/dispatch [:set-active-page :recipes])}
                {:id       :become-a-chef
                 :name     "Chef"
                 :href     (router/path-for :become-a-chef)
                 :dispatch #(rf/dispatch [:set-active-page :become-a-chef])}
                {:id       :sign-up
                 :name     "Sign up"
                 :href     (router/path-for :sign-up)
                 :dispatch #(rf/dispatch [:set-active-page :sign-up])}
                {:id       :log-in
                 :name     "Log In"
                 :href     (router/path-for :log-in)
                 :dispatch #(rf/dispatch [:set-active-page :log-in])}])

(defn public
  []
  (let [active-nav @(rf/subscribe [:active-nav])]
    (fn []
      [:div {:class "flex justify-end py-1"}
       (for [item nav-items]
         ^{:key (:id item)}
         [nav-item (assoc item :active-nav active-nav)])])))



