(ns app.auth.events
  (:require [re-frame.core :refer [reg-event-db reg-event-fx reg-cofx dispatch after]]
            [app.helpers :as h]
            [cljs.reader :refer [read-string]]))

(comment
  (* 2 2)


  (dispatch [:set-active-nav :profile])
  (dispatch [:update-profile {:first-name "Gigel" :last-name "Stoica"}])
  (read-string (h/get-item-ls "cheffy-user")))



(def cheffy-user-key "cheffy-user")

(defn set-user-ls!
  [{:keys [auth]}]
  (when (:uid auth)
    (h/set-item-ls! cheffy-user-key auth)))

(defn remove-user-ls!
  []
  (h/remove-item-ls! cheffy-user-key))

(def set-user-interceptors [(after set-user-ls!)])
(def remove-user-interceptors [(after remove-user-ls!)])

(reg-cofx
  :local-store-user
  (fn [cofx _]
    (assoc cofx :local-store-user (read-string
                                    (h/get-item-ls cheffy-user-key)))))

(reg-event-fx
  :sign-up
  set-user-interceptors
  (fn [{:keys [db]} [_ {:keys [email password first-name last-name]}]]
    {:db          (-> db
                      (assoc-in [:auth :uid] email)
                      (assoc-in [:users email] {:id      email
                                                :profile {:first-name first-name
                                                          :last-name  last-name
                                                          :email      email
                                                          :password   password
                                                          :img        "img/avatar.jpg"}
                                                :saved   #{}
                                                :inboxes {}}))
     :dispatch    [:set-active-page :saved]
     :navigate-to {:path "/saved"}}))

(reg-event-fx
  :log-in                                                   ;; cofx {:db db :dispatch [:set-active-page :saved]}
  set-user-interceptors
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
        {:db          (-> db
                          (assoc-in [:auth :uid] email)
                          (update-in [:errors] dissoc :email))
         :dispatch    [:set-active-page :saved]
         :navigate-to {:path "/saved"}}))))


(reg-event-fx
  :log-out
  (fn [{:keys [db]} _]
    {:db          (assoc-in db [:auth :uid] nil)
     :dispatch    [:set-active-page :recipes]
     :navigate-to {:path "/recipes"}}))

(reg-event-db
  :update-profile
  (fn [db [_ profile]]
    (let [uid (get-in db [:auth :uid])]
      (update-in db [:users uid :profile]
                 merge (select-keys profile [:first-name :last-name])))))

(reg-event-db
  :delete-account
  remove-user-interceptors
  (fn [db _]
    (let [uid (get-in db [:auth :uid])]
      {:db          (-> db
                        (assoc-in [:auth :uid] nil)
                        (update-in [:users] dissoc uid))
       :dispatch    [:set-active-page :recipes]
       :navigate-to "/recipes"})))
