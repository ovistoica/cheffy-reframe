(ns app.core
  (:require [reagent.core :as r]
            [re-frame.core :as rf]
            [app.db]
    ;; -- auth --
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
            [app.recipes.views.recipes :refer [recipes]]
            [app.theme :refer [cheffy-theme]]
            [app.components.core :as c]))
;(rf/reg-sub
;  :active-nav
;  (fn [db _]
;    (get-in db [:nav :active-nav])))


(defn pages
  [page-name]
  (case page-name
    :inboxes [inboxes]
    :profile [profile]
    :become-a-chef [become-a-chef]
    :recipes [recipes]
    :log-in [log-in]
    :sign-up [sign-up]
    [log-in]))


(defn app
  []
  (let [active-nav @(rf/subscribe [:active-nav])]
    [:<>
     [:div.container.mx-auto
      [:div
       [:div
        [nav]
        [pages active-nav]]]]]))

(defn ^:dev/after-load start
  []
  (rf/dispatch [:initialize-db])
  (r/render
    [app]
    (.getElementById js/document "app")))

(defn ^:export init
  []
  (start))
