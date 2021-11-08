(ns app.components.icons)

(defn left-arrow []
  [:svg {:xmlns     "http://www.w3.org/2000/svg"
         :className "h-6 w-6"
         :fill      "none"
         :viewBox   "0 0 24 24"
         :stroke    "currentColor"}
   [:path {:strokeLinecap  "round"
           :strokeLinejoin "round"
           :strokeWidth    "{2}"
           :d              "M10 19l-7-7m0 0l7-7m-7 7h18"}]])

(defn heart-outline
  ([] (heart-outline "currentColor"))
  ([fill]
   [:svg.h-6.w-6 {:xmlns   "http://www.w3.org/2000/svg"
                  :fill    "none"
                  :viewBox "0 0 24 24"
                  :stroke  (or fill "currentColor")}
    [:path {:stroke-linecap  "round"
            :stroke-linejoin "round"
            :stroke-width    "2"
            :d               "M4.318 6.318a4.5 4.5 0 000 6.364L12 20.364l7.682-7.682a4.5 4.5 0 00-6.364-6.364L12 7.636l-1.318-1.318a4.5 4.5 0 00-6.364 0z"}]]))

(defn heart-solid
  ([] (heart-solid "currentColor"))
  ([fill]
   [:svg.h-5.w-5 {:xmlns   "http://www.w3.org/2000/svg"
                  :viewBox "0 0 20 20"
                  :fill    (or fill "currentColor")}
    [:path {:fill-rule "evenodd"
            :d         "M3.172 5.172a4 4 0 015.656 0L10 6.343l1.172-1.171a4 4 0 115.656 5.656L10 17.657l-6.828-6.829a4 4 0 010-5.656z"
            :clip-rule "evenodd"}]]))


(defn clock
  []
  [:svg.h-6.w-6 {:xmlns "http://www.w3.org/2000/svg" :fill "none" :viewBox "0 0 24 24" :stroke "currentColor"}
   [:path {:stroke-linecap  "round"
           :stroke-linejoin "round"
           :stroke-width    "2"
           :d               "M12 8v4l3 3m6-3a9 9 0 11-18 0 9 9 0 0118 0z"}]])
