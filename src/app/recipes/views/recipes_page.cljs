(ns app.recipes.views.recipes-page
  (:require [app.components.page-nav :refer [page-nav]]
            [re-frame.core :as rf]
            [app.recipes.views.recipe-list :refer [recipe-list]]))

(defn recipes-page
  []
  (let [public @(rf/subscribe [:public])
        drafts @(rf/subscribe [:drafts])
        logged-in? @(rf/subscribe [:logged-in?])]
    (fn []
      [:<>
       [page-nav {:center "Recipes"}]
       (when (seq drafts)
         [:<>
          [:h4 {:class "font-bold py-2 text-2xl"} "Drafts"]
          [recipe-list drafts]])

       (when logged-in?
         [:<>
          [:h4 {:class "font-bold py-2 text-2xl"} "Public"]
          [recipe-list public]])])))







