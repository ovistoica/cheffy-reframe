(ns app.recipes.views.recipe-page
  (:require [app.components.page-nav :refer [page-nav]]
            [re-frame.core :as rf]
            [app.recipes.views.recipe-info :refer [recipe-info]]
            [app.recipes.views.recipe-image :refer [recipe-image]]
            [app.recipes.views.ingredients :refer [ingredients]]
            [reagent.core :as r]
            [app.components.core :refer [button]]
            [app.components.modal :refer [modal modal-overlay modal-header modal-content modal-body modal-footer]]))


(defn recipe-page
  []
  (let [{:keys [name]} @(rf/subscribe [:recipe])]
    (fn []
      [:div
       [page-nav {:center name}]
       [:div.flex.flex-row.w-full
        [:div.flex.flex-col
         [:div.pb-2
          [recipe-info]]
         [:div.pb-2]
         [recipe-image]
         [:div.pb-2
          [ingredients]]]
        [:div.flex.flex-col]]])))


