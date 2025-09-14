# Step 1: Build with Maven
FROM maven:3.9.6-eclipse-temurin-21 AS build

WORKDIR /app

# Copy everything in one go (no partial POM copy)
COPY . .

# Build the JAR (skip tests for faster build in Docker)
RUN mvn clean package -DskipTests

# Step 2: Run the app
FROM openjdk:21-jdk-slim

WORKDIR /app

# Copy the jar from the build stage
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8081
ENTRYPOINT ["java", "-jar", "app.jar"]

