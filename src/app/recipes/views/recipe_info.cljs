(ns app.recipes.views.recipe-info
  (:require [app.components.icons :refer [clock heart-outline heart-solid]]
            [re-frame.core :as rf]))

(defn recipe-info
  []
  (let [{:keys [id cook saved-count prep-time]} @(rf/subscribe [:recipe])
        {:keys [uid saved]} @(rf/subscribe [:user])
        logged-in? @(rf/subscribe [:logged-in?])
        saved? (contains? saved id)
        author? (= cook uid)
        can-save? (and logged-in? (not author?) (not saved?))]
    (fn []
      [:div.p-4.bg-white.rounded
       [:h5.text-xl "cook@gmail.com"]
       [:div.flex.py-2
        [:div.flex.items-center
         [:div.text-red-500
          (if saved?
            [heart-solid]
            [heart-outline])]
         [:div.pl-1 saved-count]]
        [:div.flex.items-center.pl-6
         [clock]
         [:div.pl-1 prep-time " min"]]]])))



