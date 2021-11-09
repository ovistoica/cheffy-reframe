(ns app.router
  (:require [bidi.bidi :as bidi]
            [pushy.core :as pushy]
            [re-frame.core :as rf]))

(def routes ["/" {""               :recipes
                  "become-a-chef/" :become-a-chef
                  "saved/"         :saved
                  "recipes/"       {""           :recipes
                                    [:recipe-id] :recipe}
                  "inboxes/"       {""          :inboxes
                                    [:inbox-id] :inbox}
                  "profile/"       :profile
                  "sign-up/"       :sign-up
                  "log-in/"        :log-in}])

(def history
  (let [dispatch #(rf/dispatch [:route-changed %])
        match #(bidi/match-route routes %)]
    (pushy/pushy dispatch match)))


(defn start!
  []
  (pushy/start! history))

(def path-for
  "Get the path for the screen-id or an error if it doesn't exist.
  Example:
  For route :profile => \"profile\""
  (partial bidi/path-for routes))


(defn set-token!
  [token]
  (pushy/set-token! history token))
