(ns sorcery.main
  (:require [clojure.core.server :as server])
  (:import [java.io PipedReader PipedWriter]))

(defn add [a b]
  (+ a b))

(defn before []
  (println "Stopped!"))

(defn after []
  (println "Started!"))

(defn remote-eval [code]
  (with-open [reader (PipedReader.)
              writer (PipedWriter.)]
    (.connect reader writer)
    (let [result! (promise)]
      (future
        (server/remote-prepl
          "localhost" "5555"
          reader
          (fn [msg]
            (deliver result! msg))))
      (.write writer code)
      (.flush writer)
      @result!)))

(defn -main []
  (println "Hello, World!" (add 1 2)))

(comment
  (server/start-server
    {:accept 'clojure.core.server/io-prepl
     :address "localhost"
     :port 5555
     :name "my-prepl"})
  (server/stop-server "my-prepl")
  (remote-eval "(+ 1 2)"))
