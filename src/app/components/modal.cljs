(ns app.components.modal
  (:require ["@headlessui/react" :refer [Dialog]]
            [app.helpers :refer [unescape-html classes]]
            [reagent.core :as r]
            [re-frame.core :as rf]))

; Modal

(def dialog (r/adapt-react-class Dialog))
(def dialog-overlay (r/adapt-react-class (aget Dialog "Overlay")))
(def dialog-title (r/adapt-react-class (aget Dialog "Title")))
(def dialog-description (r/adapt-react-class (aget Dialog "Description")))

(defn center-modal-contents
  "Trick the browser to center modal contents"
  []
  [:span {:class "hidden sm:inline-block sm:align-middle sm:h-screen" :aria-hidden "true"}
   (unescape-html "&#8203;")])

(defn modal-header
  [title]
  [dialog-title {:as    "h3"
                 :class "text-xl leading-6 font-semibold text-gray-800 pb-4"} title])

(defn modal-content
  [& children]
  [:div {:class "flex items-end justify-center min-h-screen pt-4 px-4 pb-20 text-center sm:block sm:p-0"}
   [center-modal-contents]
   [:div {:class (classes "inline-block align-bottom bg-white rounded-lg text-left overflow-hidden shadow-xl"
                          "transform transition-all sm:my-8 sm:align-middle sm:max-w-lg sm:w-full")}
    children]])

(defn modal-footer
  [& content]
  [:div {:class "bg-gray-50 px-4 py-3 sm:px-6 sm:flex sm:flex-row-reverse"}
   content])


(defn modal-body
  [& contents]
  [:div {:class "bg-white px-4 pt-5 pb-4 sm:p-6 sm:pb-4"}
   [:div {:class "sm:flex sm:items-start"}
    [:div {:class "mt-3 text-center sm:mt-0 sm:ml-4 sm:text-left w-full"} contents]]])


(defn modal-overlay
  "Backdrop for the modal. Used to darken background"
  []
  [dialog-overlay {:class "fixed inset-0 bg-black opacity-30"}])


(defn modal
  [{:keys [open? on-close]} & children]
  []
  [dialog {:open     open?
           :as       "div"
           :class    "fixed z-10 inset-0 overflow-y-auto"
           :on-close on-close}
   children])


(defn full-modal
  [{:keys [modal-name body footer header]}]
  (let [active-modal @(rf/subscribe [:active-modal])]
    [modal {:open?    (= active-modal modal-name)
            :on-close #(rf/dispatch [:close-modal])}
     [modal-overlay]
     [modal-content
      [modal-body
       (when header [modal-header header])
       body]
      [modal-footer footer]]]))



