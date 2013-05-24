(ns pages-test
  (:use expectations
        ring.mock.request
        compojureref.handler))

(defn index-response [] (app (request :get "/")))

(expect 200 (:status (index-response)))
(expect #"Fun with Clojure" (:body (index-response)))

(defn not-found-response [] (app (request :get "/invalid/bananas")))

(expect 404 (:status (not-found-response)))


