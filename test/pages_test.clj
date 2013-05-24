(ns pages-test
  (:use expectations
        ring.mock.request
        compojureref.handler))

(defn GET [path] (app (request :get path)))

(expect 200                 (:status  (GET "/")))
(expect #"Fun with Clojure" (:body    (GET "/")))

(expect 200                 (:status  (GET "/someword")))
(expect #"&lt;someword&gt;" (:body    (GET "/%3Csomeword%3E")))

(expect 404                 (:status  (GET "/invalid/path")))

