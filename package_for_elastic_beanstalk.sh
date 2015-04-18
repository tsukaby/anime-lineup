#!/bin/sh

skinny package:standalone

cp standalone-build/target/scala-2.11/anime-lineup* docker/anime-lineup.jar
#sed -e "s/MY_DD_API_KEY/$MY_DD_API_KEY/g" ./Dockerfile > elastic_beanstalk/Dockerfile
