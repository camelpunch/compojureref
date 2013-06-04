(defproject
  compojureref "0.1.0-SNAPSHOT"
  :description "Just a bunch of Compojure tricks"
  :url "https://github.com/camelpunch/compojureref"
  :dependencies [
                 [org.clojure/clojure "1.5.1"]
                 [compojure "1.1.5"]
                 [hiccup "1.0.3"]
                 [com.datomic/datomic-free "0.8.3971"]
                 ]
  :plugins [
            [lein-ring "0.8.3"]
            [lein-expectations "0.0.7"]
            [lein-autoexpect "0.2.5"]
            ]
  :ring {:handler compojureref.routes/app}
  :profiles
  {:dev {:dependencies [
                        [ring-mock "0.1.3"]
                        [expectations "1.4.38"]
                        [org.clojure/tools.nrepl "0.2.3"]
                        ]}})
