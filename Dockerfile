FROM openjdk:21-jdk-slim

# Add a volume for storing temporary files or logs if necessary
VOLUME /tmp

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file from the build output into the Docker image
COPY ./build/libs/player-service-0.0.1-SNAPSHOT.jar app.jar

# Expose the application port (default is 8080)
EXPOSE 8081

# Run the JAR file
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
