#!/usr/bin/env bash
if [ "$TRAVIS_BRANCH" = 'master' ] && [ "$TRAVIS_PULL_REQUEST" == 'false' ]; then
    mvn release:prepare -P sign,build-extras,!build-extras --settings cd/mvnsettings.xml
    mvn release:perform -P sign,build-extras,!build-extras --settings cd/mvnsettings.xml
fi