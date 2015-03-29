## anime-lineup

We introduce Japanese animations to you!
This is web application.

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