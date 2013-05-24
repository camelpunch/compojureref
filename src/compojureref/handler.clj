(ns compojureref.handler
  (:use compojure.core)
  (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [compojureref.views :as views]))

(defroutes app-routes
           (GET "/" [] (views/index-page))
           (GET "/:word" [word] (views/show-word word))
           (route/resources "/")
           (route/not-found "Not Found"))

(def app (handler/site app-routes))
