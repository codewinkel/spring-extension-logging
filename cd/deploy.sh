#!/bin/bash
cd `dirname $0`/..

if [ -z "$JIRA_USERNAME" ]
then
    echo "error: please set JIRA_USERNAME and JIRA_PASSWORD environment variable"
    exit 1
fi

if [ -z "$JIRA_PASSWORD" ]
then
    echo "error: please set JIRA_PASSWORD environment variable"
    exit 1
fi

if [ ! -z "$TRAVIS_TAG" ]
then
    echo "on a tag -> set pom.xml <version> to $TRAVIS_TAG"
    mvn --settings ./cd/mvnsettings.xml org.codehaus.mojo:versions-maven-plugin:2.1:set -DnewVersion=$TRAVIS_TAG 1>/dev/null 2>/dev/null
else
    echo "not on a tag -> keep snapshot version in pom.xml"
fi

mvn clean deploy --settings ./cd/mvnsettings.xml -DskipTests=true -B -U