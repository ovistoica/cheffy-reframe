(ns app.nav.views.nav-item)

(defn nav-item
  [{:keys [id href name dispatch active-page]}]
  (let [active? (= active-page id)]
    [:a {:key      id
         :href     href
         :class    (str "pb-2 mx-3 " (when active? "text-green-500 border-green-500 border-b-2"))
         :pb       10
         :on-click dispatch} name]))
