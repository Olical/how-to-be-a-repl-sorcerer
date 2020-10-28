#!/usr/bin/env sh

set -xe

clj -A:nrepl -m nrepl.cmdline --middleware [cider.nrepl/cider-middleware]
