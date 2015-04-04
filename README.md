# anime-lineup

We introduce Japanese animations to you!
This is web application.

[![Build Status](https://travis-ci.org/tsukaby/anime-lineup.svg?branch=master)](https://travis-ci.org/tsukaby/anime-lineup)

### Docker run

```
# Preparation
boot2docker init
boot2docker up

# setup DOCKER environments

# set NEWRELIC_LICENSE_KYE
export NEWRELIC_LICENSE_KYE=xxxxxxxxxxxxx

# build
docker build -t tsukaby/anime-lineup .
 
# run
docker run -d -p 80:8080 tsukaby/anime-lineup

# setup VirtualBox port forward. For example, Host port 80 to guest port 80

# Access above Web application on Docker. For example,
http://localhost

```

### Docker deploy to Elastic Beanstalk

```
# set NEWRELIC_LICENSE_KEY
export NEWRELIC_LICENSE_KEY=xxxxxxxxxxxxx

# build
./package_for_elastic_beanstalk.sh

# Upload zip file to Elastic Beanstalk
```
