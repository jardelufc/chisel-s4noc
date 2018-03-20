#!/usr/bin/env bash
args=$@
sbt "test:run-main patmos.Launcher $args"
