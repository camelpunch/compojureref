(ns compojureref.views
  (:use [hiccup page util]))

(defn index-page []
  (html5
    [:head [:title "Compojure Fun"]]
    [:body
     [:h1 (escape-html (str "Fun with Clojure and Compojure"))]
     [:a {:href "/<script>"} "Escaped HTML"]
     ]))

(defn show-word [word]
  (html5
    [:head [:title "An escaped word"]]
    [:body
     [:h1 (escape-html (str "An escaped word"))]
     [:p "Here is your escaped word: " (escape-html word)]
     [:a {:href (url "/")} "« back"]
     ]))

