(ns pages-test
  (:use expectations
        ring.mock.request
        compojureref.routes))

(condp
(defn GET [path] (app (request :get path)))
(defn POST [path params] (app (request :post path params)))
(defn PUT [path params] (app (request :put path params)))

(expect 200 (:status (GET "/")))
(expect #"Fun with Clojure" (:body (GET "/")))

(expect 200 (:status (GET "/someword")))
; escapes HTML tags
(expect #"&lt;someword&gt;" (:body (GET "/%3Csomeword%3E")))

(expect 404 (:status (GET "/invalid/path")))

(expect 201 (:status (POST "/sandwiches" {:type "ploughmans"})))
(expect #"/sandwiches/[^/]+" (:Location
                               (:headers
                                 (POST "/sandwiches" {:type "hummus"}))))

(expect 204 (:status (PUT "/sandwiches/throwaway" {:type "processed-cheese"})))
(expect 200 (let [path (str "/sandwiches/" (java.util.UUID/randomUUID))]
              (PUT path {:type "potato"})
              (:status (GET path))))
(expect 404 (:status (GET "/sandwiches/never-made")))
