#
# Build stage
#
FROM maven:3.6.0-jdk-11-slim AS build
# install ssh-agent, git, docker, maven
RUN apk add --no-cache \
    git \
    maven \
    bash \
    curl wget

# install java based on https://github.com/docker-library/openjdk/blob/4e39684901490c13eaef7892c44e39043d7c4bed/8-jdk/alpine/Dockerfile
RUN { \
                echo '#!/bin/sh'; \
                echo 'set -e'; \
                echo; \
                echo 'dirname "$(dirname "$(readlink -f "$(which javac || which java)")")"'; \
        } > /usr/local/bin/docker-java-home \
        && chmod +x /usr/local/bin/docker-java-home
ENV JAVA_HOME /usr/lib/jvm/java-1.8-openjdk
ENV PATH $PATH:/usr/lib/jvm/java-1.8-openjdk/jre/bin:/usr/lib/jvm/java-1.8-openjdk/bin
ENV JAVA_VERSION 8u121
ENV JAVA_ALPINE_VERSION 8.121.13-r0
RUN set -x \
        && apk add --no-cache \
                openjdk8="$JAVA_ALPINE_VERSION" \
        && [ "$JAVA_HOME" = "$(docker-java-home)" ]

#
# Package stage
#
FROM openjdk:11-jre-slim
COPY --from=build /home/app/target/springboot-kotlin-crud-0.0.1-SNAPSHOT.jar /usr/local/lib/demo.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/local/lib/demo.jar"]