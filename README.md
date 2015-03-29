# anime-lineup

We introduce Japanese animations to you!
This is web application.

[![Build Status](https://travis-ci.org/tsukaby/anime-lineup.svg?branch=master)](https://travis-ci.org/tsukaby/anime-lineup)

### Docker

```
# Preparation
boot2docker init
boot2docker up

# setup DOCKER_ENV

# compile
docker build -t tsukaby/anime-lineup .

# run
docker run -d -p 80:8080 tsukaby/anime-lineup
```