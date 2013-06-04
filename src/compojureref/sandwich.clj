(ns compojureref.sandwich
  (:use [datomic.api :only [q db] :as d]))

(defn find-sandwich
  "Return a sandwich from the database given an ID"
  [id conn]
  (first (d/q '[:find ?sandwich
                :in $ ?search-id
                :where [?sandwich :sandwich/id ?search-id]]
              (d/db conn) id)))

(defn create-sandwich
  "Create a sandwich in the database"
  [id conn]
  (d/transact conn
              [{:db/id (d/tempid :db.part/user)
                :sandwich/id id}]))
