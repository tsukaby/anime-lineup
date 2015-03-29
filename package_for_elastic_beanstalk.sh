#!/bin/sh

skinny package:standalone

rm -rf elastic_beanstalk
mkdir -p elastic_beanstalk/standalone-build/target/scala-2.11

cp standalone-build/target/scala-2.11/anime-lineup* elastic_beanstalk/standalone-build/target/scala-2.11/
cp Dockerfile elastic_beanstalk/
cp Dockerrun.aws.json elastic_beanstalk/

cd elastic_beanstalk
zip -r ../app ./*
cd ..

rm -rf elastic_beanstalk
