(ns compojureref.storage
  (:use [datomic.api :only [q db] :as d]))

(defn connection [db-name schema]
  (doto (d/connect
          (doto (str "datomic:mem://" db-name)
            d/create-database))
    (d/transact schema)))


