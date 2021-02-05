#!/bin/sh

(cd .. && mvn clean install)

java -jar ../target/record-classifier-1.0-SNAPSHOT-jar-with-dependencies.jar config.yml

