FROM ubuntu:latest
LABEL authors="skrys"

ENTRYPOINT ["top", "-b"]

FROM eclipse-temurin:17-jdk-alpine

WORKDIR /opn-service

COPY target/opn-service-0.0.2-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
