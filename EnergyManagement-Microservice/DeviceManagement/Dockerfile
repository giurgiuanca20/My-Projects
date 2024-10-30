# First stage: Build the application with Maven
FROM maven:3.9.0-eclipse-temurin-19 as BUILDER

# Copy the project files to the builder stage
COPY ./pom.xml /root/
COPY ./src/ /root/src
COPY ./checkstyle.xml /root/

# Set the working directory
WORKDIR /root

# Run Maven to package the application
RUN mvn clean package

# Second stage: Create a lightweight image with the built application
FROM openjdk:17-jdk-slim

# Set the working directory in the second stage
WORKDIR /app

# Copy the jar file from the builder stage
COPY --from=builder /root/target/DeviceManagement-0.0.1-SNAPSHOT.jar /app/device-service.jar

# Set the timezone
ENV TZ=UTC

# Set the entry point to run the application
ENTRYPOINT ["java", "-jar", "/app/device-service.jar"]
