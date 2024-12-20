#FROM ubuntu:latest
#LABEL authors="skrys"
# Use Maven to build the application
FROM eclipse-temurin:17-jdk-alpine as builder

WORKDIR /app

COPY pom.xml .
RUN apk add --no-cache maven && mvn dependency:go-offline

COPY src ./src
RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jdk-alpine

WORKDIR /opn-service

COPY --from=builder /app/target/opn-service-0.0.2-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
