(ns app.components.page-nav
  (:require [app.components.icons :refer [left-arrow]]))

(defn page-nav
  [{:keys [left center right]}]
  [:div {:class "py-2 flex justify-between"}
   [:div left
    (when left
      [:a {:aria-label "Back"
           :href       left}]
      [left-arrow])]
   [:div
    [:h2 {:class "py-20 font-bold"} center]]
   [:div (when right right)]])


