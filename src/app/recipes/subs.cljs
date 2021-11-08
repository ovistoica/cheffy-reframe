(ns app.recipes.subs
  (:require [re-frame.core :refer [reg-sub]]))

(reg-sub
  :drafts
  (fn [db _]
    (let [recipes (vals (:recipes db))
          uid (get-in [:auth :uid] db)
          filters [#(= (:public? % false))
                   #(= (:cook % uid))]]
      (filter (apply every-pred filters) recipes))))

(reg-sub
  :public
  (fn [db _]
    (let [recipes (vals (:recipes db))]
      (filter #(= (:public? % true)) recipes))))