(ns app.nav.views.nav
  (:require
    [app.nav.views.public :refer [public]]
    [app.nav.views.authenticated :refer [authenticated]]
    [re-frame.core :as rf]))

(defn nav
  []
  (let [logged-in? @(rf/subscribe [:logged-in?])]
    (if logged-in?
      [authenticated]
      [public])))
