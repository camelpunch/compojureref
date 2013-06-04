(ns compojureref.routes
  (:use compojure.core)
  (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [compojureref.actions :as actions]
            [compojureref.views :as views]))

(defroutes app-routes
  (GET "/" []
       (views/index-page))

  (GET "/:word" [word]
       (views/show-word word))

  (POST "/sandwiches" [params]
        (actions/create-sandwich (:type params)))

  (PUT "/sandwiches/:id" [id & params]
       (actions/create-sandwich-with-id id (:type params)))

  (GET "/sandwiches/:id" [id]
       (actions/show-sandwich id))

  (route/resources "/")
  (route/not-found "Not Found"))

(def app (handler/site app-routes))
