(ns app.nav.events
  (:require [re-frame.core :refer [reg-event-db reg-fx]]
            [app.router :as router]))

(reg-fx
  :navigate-to
  (fn [{:keys [path]}]
    (router/set-token! path)))

(reg-event-db
  :set-active-nav
  (fn [db [_ active-nav]]
    (assoc-in db [:nav :active-nav] active-nav)))

(reg-event-db
  :route-changed
  (fn [db [_ {:keys [handler route-params]}]]
    (let [recipe-id (:recipe-id route-params)]
      (cond-> db
              true (assoc-in [:nav :active-page] handler)
              recipe-id (assoc-in [:nav :active-recipe] (keyword recipe-id))))))

(reg-event-db
  :open-modal
  (fn [db [_ active-modal]]
    (assoc-in db [:nav :active-modal] active-modal)))

(reg-event-db
  :close-modal
  (fn [db _]
    (assoc-in db [:nav :active-modal] nil)))
