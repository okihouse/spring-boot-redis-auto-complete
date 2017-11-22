#!/bin/sh
cd $TRAVIS_BUILD_DIR/spring-boot-redis-autocomplete-example
mvn clean -Dmaven.test.skip=true
