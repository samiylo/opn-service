FROM ubuntu:latest
LABEL authors="skrys"

ENTRYPOINT ["top", "-b"]

FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

COPY target/opn-service.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
