# https://www.docker.com/blog/kickstart-your-spring-boot-application-development/
# FROM eclipse-temurin:17-jdk-alpine
# WORKDIR /app
# COPY .mvn/ ./mvn
# COPY mvnw pom.xml /app/
# RUN ./app/mvnw dependency:go-offline
# COPY src /app/src
# CMD ["./mvnw", "spring-boot:run"]


# -----------------------------------------

# https://medium.com/@bubu.tripathy/dockerizing-your-spring-boot-application-75bf2c6568d0
# First stage: build the application
# FROM maven:3.8.3-jdk-11 AS build                      # need java-17 maven version
# FROM maven:3.9.6-eclipse-temurin-17-alpine AS build   # this was causing certificate issues when talking to FakeStore API
FROM maven:3.9.6-amazoncorretto-17 AS build
COPY . /app
WORKDIR /app
RUN mvn package -DskipTests

# Second stage: create a slim image
# FROM openjdk:11-jre-slim                              # this is deprecated
# FROM eclipse-temurin:17-jdk-alpine                    # this was causing certificate issues when talking to FakeStore API
FROM amazoncorretto:17.0.10
COPY --from=build /app/target/*.jar /app.jar
COPY dev.env.yaml /dev.env.yaml

# expose port & ser SERVER_PORT for spring boot to run on that port
EXPOSE 8085
ENV SERVER_PORT=8085

# create db/ folder for .sqlite file
RUN mkdir -p /db
ENTRYPOINT ["java", "-jar", "/app.jar"]