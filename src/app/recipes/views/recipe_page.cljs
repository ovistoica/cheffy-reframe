(ns app.recipes.views.recipe-page
  (:require [app.components.page-nav :refer [page-nav]]
            [re-frame.core :as rf]))

(defn recipe-page
  []
  (let [{:keys [name]} @(rf/subscribe [:recipe])]
    (fn []
      [:div
       [page-nav {:center name}]
       [:div.flex.flex-row.
        [:div.flex.flex-col.max-w-6xl
         [:div.pb-2 "recipe info"]
         [:div.pb-2 "recipe image"]
         [:div.pb-2 "recipe-ingredients"]]
        [:div.flex.flex-col.max-w-6xl
         [:div.pb-2 "recipe steps"]]]])))

