(ns pages-test
  (:use expectations
        ring.mock.request
        compojureref.routes))

(defn GET [path] (app (request :get path)))
(defn POST [path params] (app (request :post path params)))
(defn PUT [path params] (app (request :put path params)))

; root page success
(expect 200                     (:status  (GET "/")))
(expect #"Fun with Clojure"     (:body    (GET "/")))

; root with id
(expect 200                     (:status  (GET "/someword")))
; escapes HTML tags
(expect #"&lt;someword&gt;"     (:body    (GET "/%3Csomeword%3E")))

(expect 404                     (:status  (GET "/invalid/path")))

; create sandwich, given location of new sandwich
(expect 201                     (:status
                                  (POST "/sandwiches" {:type "ploughmans"})))
(expect #"/sandwiches/[^/]+"    (:Location
                                  (:headers
                                    (POST "/sandwiches" {:type "hummus"}))))

; PUT sandwich, can get afterwards
(expect 204                     (:status
                                  (PUT "/sandwiches/throwaway" {:type "processed-cheese"})))
(expect 200                     (do
                                  (PUT "/sandwiches/my-sandwich" {:type "potato"})
                                  (:status (GET "/sandwiches/my-sandwich"))))
; GETing non-existant 404s
(expect 404                     (:status (GET "/sandwiches/never-made")))
