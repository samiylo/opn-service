FROM ubuntu:latest
LABEL authors="skrys"

ENTRYPOINT ["top", "-b"]

# Use the official OpenJDK 17 image as a base
FROM eclipse-temurin:17-jdk-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the application JAR file into the container
COPY target/your-application.jar app.jar

# Expose the port your application runs on
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
