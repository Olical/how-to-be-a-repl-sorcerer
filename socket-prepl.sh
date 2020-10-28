#!/usr/bin/env sh

set -xe

clj -J-Dclojure.server.jvm="{:port 5555 :accept clojure.core.server/io-prepl}"
