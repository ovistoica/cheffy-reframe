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

(reg-sub
  :recipe
  (fn [db _]
    (let [recipe-id (get-in db [:nav :active-recipe])]
      (get-in db [:recipes recipe-id]))))

(reg-sub
  :ingredients
  (fn [db _]
    (let [recipe-id (get-in db [:nav :active-recipe])]
      (vals (get-in db [:recipes recipe-id :ingredients])))))

(reg-sub
  :author?
  (fn [db _]
    (let [uid (get-in db [:auth :uid])
          active-recipe (get-in db [:nav :active-recipe])
          recipe (get-in db [:recipes active-recipe])]
      (= (:cook recipe) uid))))
