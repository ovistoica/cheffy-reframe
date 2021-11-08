(ns app.recipes.views.recipe-list
  (:require [app.recipes.views.recipe-card :refer [recipe-card]]))

(defn recipe-list
  [items]
  [:div {:class "cards"}
   (for [recipe items]
     ^{:key (:id recipe)}
     [recipe-card recipe])])
