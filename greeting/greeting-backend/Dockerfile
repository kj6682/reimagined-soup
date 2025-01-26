# syntax=docker/dockerfile:1.7.0

# Builder stage
FROM --platform=$BUILDPLATFORM maven:3.9.9-eclipse-temurin-21 AS builder
WORKDIR /workdir/server
COPY pom.xml /workdir/server/pom.xml
RUN mvn dependency:go-offline

COPY src /workdir/server/src
RUN mvn install

# Development environment stage
FROM builder AS dev-envs
RUN apt-get update && apt-get install -y --no-install-recommends git && \
    rm -rf /var/lib/apt/lists/*

RUN useradd -s /bin/bash -m idea && \
    groupadd docker && \
    usermod -aG docker idea

# Install Docker tools (cli, buildx, compose)
COPY --from=gloursdocker/docker / /
CMD ["mvn", "spring-boot:run"]

# Prepare production stage
FROM builder AS prepare-production
RUN mkdir -p target/dependency
WORKDIR /workdir/server/target/dependency
RUN jar -xf ../*.jar

# Final stage
FROM eclipse-temurin:21-jre-alpine

EXPOSE 8080
VOLUME /tmp
ARG DEPENDENCY=/workdir/server/target/dependency
COPY --from=prepare-production ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=prepare-production ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=prepare-production ${DEPENDENCY}/BOOT-INF/classes /app
ENTRYPOINT ["java","-cp","app:app/lib/*","org.kj6682.greetingbackend.GreetingBackendApplication"]
