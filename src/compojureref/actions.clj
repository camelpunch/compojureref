(ns compojureref.actions
  (:use [ring.util response])
  (:use [datomic.api :only [q db] :as d])
  (:require [compojureref.storage :as s])
  (:require [compojureref.sandwich :as sandwich]))

(def conn (s/connection "sandwiches"
                        [{:db/id #db/id[:db.part/db]
                          :db/ident :sandwich/id
                          :db/valueType :db.type/string
                          :db/unique :db.unique/value
                          :db/cardinality :db.cardinality/one
                          :db/doc "A sandwich's public ID"
                          :db.install/_attribute :db.part/db}]))

(defn create-sandwich [type]
  (header {:status 201} :Location "/sandwiches/1"))

(defn create-sandwich-with-id [id type]
  (sandwich/create-sandwich id conn)
  {:status 204})

(defn show-sandwich [id]
  {:status (if (sandwich/find-sandwich id conn) 200 404)})
