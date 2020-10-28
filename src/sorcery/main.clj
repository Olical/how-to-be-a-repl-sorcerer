(ns sorcery.main
  (:require [clojure.core.server :as server])
  (:import [java.io PipedReader PipedWriter]))

(defn add [a b]
  (+ a b))

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
  (remote-eval "(+ 1 2)"))
