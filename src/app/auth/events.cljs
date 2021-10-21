(ns app.auth.events
  (:require [re-frame.core :refer [reg-event-db reg-event-fx]]))


(reg-event-fx
  :sign-up
  (fn [{:keys [db]} [_ {:keys [email password first-name last-name]}]]
    {:db       (-> db
                   (assoc-in [:auth :uid] email)
                   (assoc-in [:users email] {:id      email
                                             :profile {:first-name first-name
                                                       :last-name  last-name
                                                       :email      email
                                                       :password   password
                                                       :img        "img/avatar.jpg"}
                                             :saved   #{}
                                             :inboxes {}}))
     :dispatch [:set-active-nav :saved]}))

(reg-event-fx
  :log-in                                                   ;; cofx {:db db :dispatch [:set-active-nav :saved]}
  (fn [cofx [_ {:keys [email password]}]]
    (let [db (:db cofx)
          user (get-in db [:users email])
          correct-password? (= (get-in user [:profile :password]) password)]
      (cond
        (not user)
        {:db (assoc-in db [:errors :email] "User not found")}
        (not correct-password?)
        {:db (assoc-in db [:errors :email] "Wrong password")}
        correct-password?
        {:db       (-> db
                       (assoc-in [:auth :uid] email)
                       (update-in [:errors] dissoc :email))
         :dispatch [:set-active-nav :saved]}))))



