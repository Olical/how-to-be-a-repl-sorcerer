#!/usr/bin/env sh

set -xe

echo "(require 'sorcery.main) (sorcery.main/-main) :repl/quit" | nc localhost 5555
