(ns app.recipes.views.recipe-card
  (:require [app.router :as router]
            [app.components.icons :refer [heart-solid heart-outline clock]]))

(defn recipe-card
  [{:keys [name saved-count prep-time img id]}]
  [:a.recipe-card.text-gray-600 {:href (router/path-for :recipes)}
   [:img.img-card {:src (or img "/img/placeholder.jpg")
                   :alt name}]

   [:div.py-2.pl-2
    [:h6.font-bold name]]
   [:div.flex.pl-2.pb-2
    [:div.flex.items-center
     [heart-outline]
     [:div.pl-1 saved-count]]
    [:div.flex.items-center.pl-6
     [clock]
     [:div.pl-1 prep-time " min"]]]])
