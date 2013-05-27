(ns compojureref.actions
  (:use [hiccup page util])
  (:use [ring.util response]))

(use '[datomic.api :only [q db] :as d])

(def uri "datomic:mem://sandwiches")
(d/create-database uri)
(def conn (d/connect uri))

(defn add-sandwich-id-attribute []
  (d/transact conn [{:db/id #db/id[:db.part/db]
                     :db/ident :sandwich/id
                     :db/valueType :db.type/string
                     :db/unique :db.unique/value
                     :db/cardinality :db.cardinality/one
                     :db/doc "A sandwich's public ID"
                     :db.install/_attribute :db.part/db}]))
(add-sandwich-id-attribute)

(defn create-sandwich [type]
  (def resp {:status 201})
  (header resp :Location "/sandwiches/1"))

(defn create-sandwich-with-id [id type]
  (do
    (d/transact conn
                [{:db/id (d/tempid :db.part/user)
                  :sandwich/id id}])
    {:status 204}))

(defn show-sandwich [id]
  (if (empty?
        (d/q '[:find ?sandwich
               :in $ ?search-id
               :where [?sandwich :sandwich/id ?search-id]]
             (d/db conn) id))
    {:status 404}
    {:status 200}))
