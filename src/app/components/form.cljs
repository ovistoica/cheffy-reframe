(ns app.components.form)

(defn get-value
  [^js/Event e]
  (.. e -target -value))

(defn form-group
  [{:keys [id label type values placeholder]}]
  [:div.mb-4
   [:label.block.text-gray-700.text-sm.font-bold.mb-2 {:for id}
    label
    [:input
     {:class       "shadow appearance-none border rounded-full w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
      :id          id
      :type        type
      :placeholder placeholder
      :value       (id @values)
      :on-change   #(swap! values assoc id (get-value %))}]]])
