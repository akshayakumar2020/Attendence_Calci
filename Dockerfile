# Stage 1: Build
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY . .
# Force Maven to clean old files and build fresh
RUN mvn clean package -DskipTests

# Stage 2: Run
FROM openjdk:17.0.1-jdk-slim
WORKDIR /app
# We look for the jar in the /app/target folder from the build stage
COPY --from=build /app/target/demo-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]