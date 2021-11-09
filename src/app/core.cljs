(ns app.core
  (:require [reagent.core :as r]
            [reagent.dom :as dom]
            [re-frame.core :as rf]
            [app.db]
            [app.router :as router]
    ;; -- auth --
            [app.auth.events]
            [app.auth.subs]
            [app.auth.views.profile :refer [profile]]
            [app.auth.views.sign-up :refer [sign-up]]
            [app.auth.views.log-in :refer [log-in]]
    ;; -- become-a-chef --
            [app.become-a-chef.views.become-a-chef :refer [become-a-chef]]
    ;; -- inbox --
            [app.inbox.views.inboxes :refer [inboxes]]
    ;; -- nav --
            [app.nav.views.nav :refer [nav]]
            [app.nav.events]
            [app.nav.subs :as nav-subs]
    ;; -- recipes --
            [app.recipes.views.recipes-page :refer [recipes-page]]
            [app.recipes.views.recipe-page :refer [recipe-page]]
            [app.recipes.subs]
            [app.theme :refer [cheffy-theme]]
            [app.components.core :as c]))


(defn pages
  [page-name]
  (case page-name
    :inboxes [inboxes]
    :profile [profile]
    :become-a-chef [become-a-chef]
    :recipes [recipes-page]
    :recipe [recipe-page]
    :log-in [log-in]
    :sign-up [sign-up]
    [recipes-page]))


(defn app
  []
  (let [active-page @(rf/subscribe [:active-page])]
    [:<>
     [:div.bg-gray-100.w-screen.h-screen
      [:div.container.mx-auto
       [:div
        [nav]
        [pages active-page]]]]]))

(defn ^:dev/after-load start
  []
  (dom/render
    [app]
    (.getElementById js/document "app")))

(defn ^:export init
  []
  (router/start!)
  (rf/dispatch-sync [:initialize-db])
  (start))
