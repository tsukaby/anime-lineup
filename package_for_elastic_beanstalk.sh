#!/bin/sh

skinny package:standalone

rm -rf elastic_beanstalk
mkdir -p elastic_beanstalk/standalone-build/target/scala-2.11

cp standalone-build/target/scala-2.11/anime-lineup* elastic_beanstalk/standalone-build/target/scala-2.11/
cp Dockerrun.aws.json elastic_beanstalk/
sed -e "s/NEWRELIC_LICENSE_KEY/$NEWRELIC_LICENSE_KEY/g" ./Dockerfile > elastic_beanstalk/Dockerfile



cd elastic_beanstalk
zip -r ../app ./*
cd ..

rm -rf elastic_beanstalk
