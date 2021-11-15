(ns app.recipes.views.recipe-image
  (:require [re-frame.core :as rf]
            [app.helpers :refer [classes]]
            [reagent.core :as r]
            [app.components.form :refer [form-group]]
            [app.components.core :refer [button button-link]]
            [app.components.modal :as m]))

(defn recipe-image
  []
  (let [initial-values {:img ""}
        values (r/atom initial-values)
        {:keys [img name img]} @(rf/subscribe [:recipe])
        author? @(rf/subscribe [:author?])
        save (fn [img]
               (rf/dispatch [:upsert-image img])
               (reset! values initial-values))]

    (fn []
      (let [active-modal @(rf/subscribe [:active-modal])]
        [:<>
         [:div [:img {:class    (classes (when author? "editable") "h-[400px] object-cover w-full rounded")
                      :src      (or img "/img/placeholder.jpg")
                      :alt      name
                      :on-click #(when author?
                                   (rf/dispatch [:open-modal :image-editor]))}]]
         #_(when author?
             [m/modal {:open?    (= active-modal :image-editor)
                       :on-close #(rf/dispatch [:close-modal])}
              [m/modal-overlay]
              [m/modal-content
               [m/modal-body
                [form-group {:id     :img
                             :type   :text
                             :label  "URL"
                             :values values}]]
               [m/modal-footer
                [:<>
                 [button {:on-click #(save (:img @values))} "Save"]
                 [button {:variant  :secondary
                          :class    (classes "mx-4")
                          :on-click #(rf/dispatch [:close-modal])}
                  "Cancel"]]]]])
         (when author?
           [m/full-modal {:modal-name :image-editor
                          :header     "Image"
                          :body       [form-group {:id     :img
                                                   :type   :text
                                                   :label  "URL"
                                                   :values values}]
                          :footer     [:<>
                                       [button {:on-click #(save (:img @values))} "Save"]
                                       [button {:variant  :secondary
                                                :class    (classes "mx-4")
                                                :on-click #(rf/dispatch [:close-modal])}
                                        "Cancel"]]}])]))))










