(ns app.recipes.views.ingredients
  (:require [re-frame.core :as rf]
            [app.components.core :refer [button button-link]]
            [app.components.icons :refer [plus]]
            [reagent.core :as r]
            [app.components.modal :as m]
            [app.components.form :refer [form-group]]
            [clojure.string :as str]))

(defn ingredients
  []
  (let [initial-values {:id nil :amount 0 :measure "" :name ""}
        values (r/atom initial-values)
        open-modal (fn [{:keys [modal-name ingredient]}]
                     (rf/dispatch [:open-modal modal-name])
                     (reset! values ingredient))
        save (fn [{:keys [id amount measure name]}]
               (rf/dispatch [:upsert-ingredient {:id      (or id (keyword (str "ingredient-" (random-uuid))))
                                                 :name    (str/trim name)
                                                 :amount  (js/parseInt amount 10)
                                                 :measure (str/trim measure)}])
               (reset! values initial-values))]

    (fn []
      (let [author? @(rf/subscribe [:author?])
            ingredients @(rf/subscribe [:ingredients])]
        [:div.p-4.bg-white.rounded.my-4
         [:div.flex.items-center.py-2
          [:h5.font-semibold.text-lg.my-4 "Ingredients"]
          [button {:variant  :secondary
                   :on-click #(open-modal {:modal-name :ingredient-editor
                                           :ingredient initial-values})
                   :class    "hover:text-white m-2 px-1 hover:bg-green-400"} [plus {:class "h-3 w-5"}]]]
         [:div.max-w-sm
          (for [{:keys [id amount measure name] :as ingredient} ingredients]
            ^{:key id} [:div.grid.grid-cols-2.text-gray-700.text-base
                        (when author? {:on-click #(open-modal {:modal-name :ingredient-editor
                                                               :ingredient ingredient})
                                       :class    "editable"})
                        [:div.grid.grid-cols-1>p.my-1 amount " " measure]

                        [:div.grid.grid-cols-1>p.my-1 name]])
          (when author?
            [m/full-modal {:modal-name :ingredient-editor
                           :header     "Ingredient"
                           :body       [:<>
                                        [:div.grid.grid-cols-2.gap-2
                                         [form-group {:id     :amount
                                                      :label  "Amount"
                                                      :type   "number"
                                                      :values values}]
                                         [form-group {:id     :measure
                                                      :label  "Measure"
                                                      :type   "text"
                                                      :values values}]]
                                        [form-group {:id     :name
                                                     :label  "Name"
                                                     :type   "text"
                                                     :values values}]]
                           :footer     [:div.flex.items-center
                                        (when-let [ingredient-id (:id @values)]
                                          [button-link {:href     "#"
                                                        :variant  :secondary
                                                        :on-click #(when (js/confirm "Are you sure?")
                                                                     (rf/dispatch [:delete-ingredient ingredient-id]))} "Delete"])
                                        [button {:variant  :secondary
                                                 :class    "mx-2"
                                                 :on-click #(rf/dispatch [:close-modal])} "Cancel"]
                                        [button {:on-click #(save @values)} "Save"]]}])]]))))



