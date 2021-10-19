(ns app.nav.views.nav-item)

(defn nav-item
  [{:keys [id href name dispatch active-nav]}]
  (let [active? (= active-nav id)]
    [:a {:key      id
         :href     href
         :class    (str "pb-2 ml-2 " (when active? "text-green-500 border-green-500 border-b-2"))
         :pb       10
         :on-click dispatch} name]))
