(ns sorcery.main-test
  (:require [clojure.test :as t]
            [sorcery.main :as main]))

(t/deftest add
  (t/is (= 10 (main/add 8 2))))
