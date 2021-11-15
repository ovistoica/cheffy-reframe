(ns app.components.headless-ui
  (:require ["@headlessui/react" :refer [Disclosure Menu Switch Transition Tab Dialog]]
            ["@heroicons/react/solid" :refer [ChevronUpIcon]]
            ["react" :refer [Fragment]]
            [reagent.core :as r]))

(def disclosure (r/adapt-react-class Disclosure))
(def disclosure-button (r/adapt-react-class (aget Disclosure "Button")))
(def disclosure-panel (r/adapt-react-class (aget Disclosure "Panel")))

(def menu (r/adapt-react-class Menu))
(def menu-items (r/adapt-react-class (aget Menu "Items")))
(def menu-item (r/adapt-react-class (aget Menu "Item")))

(def switch (r/adapt-react-class Switch))

(def transition (r/adapt-react-class Transition))
(def transition-child
  "Used to coordinate multiple transitions based on the same event.
  Needs to be nested inside a transition element."
  (r/adapt-react-class (aget Transition "Child")))
(def transition-root
  (r/adapt-react-class (aget Transition "Root")))

(def fragment (r/adapt-react-class Fragment))

; Tabs
(def tab
  "Correct usage:
  [tab-group
   [tab-list
     [tab \"1\"]
     [tab \"2\"]
     [tab \"3\"]]
   [tab-panels
     [tab-panel \"1 Content\"]
     [tab-panel \"1 Content\"]
     [tab-panel \"2 Content\"]]]"
  (r/adapt-react-class Tab))
(def tab-group (r/adapt-react-class (aget Tab "Group")))
(def tab-list (r/adapt-react-class (aget Tab "List")))
(def tab-panels (r/adapt-react-class (aget Tab "Panels")))
(def tab-panel (r/adapt-react-class (aget Tab "Panel")))




;;;;;;;;;;;;;;;;;;;;;;;; Usages context ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

#_(defn- example-usage-tailwind-context
    []
    [:div {:class "w-full px-4 pt-16"}
     [:div {:class "w-full max-w-md p-2 mx-auto bg-white rounded-2xl"}
      [disclosure
       ;; here is how you define context
       (fn [disclosure-context]
         (let [props (js->clj disclosure-context :keywordize-keys true)]
           ;; The rest of the form needs to use r/as-element for returning
           (r/as-element
             [:<>
              [disclosure-button {:class "flex justify-between w-full px-4 py-2 text-sm font-medium text-left text-purple-900 bg-purple-100 rounded-lg hover:bg-purple-200 focus:outline-none focus-visible:ring focus-visible:ring-purple-500 focus-visible:ring-opacity-75"}
               [:span "What is your refund policy?"]
               [:> ChevronUpIcon {:class (str (when (:open props) "transform rotate-180 ") "w-5 h-5 text-purple-500")}]]
              [disclosure-panel {:class "px-4 pt-4 pb-2 text-sm text-gray-500"}
               "If you're unhappy with your purchase for any reason, email us within 90 days and we'll refund you in full, no questions asked."]])))]]])
