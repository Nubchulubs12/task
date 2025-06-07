# Use an official JDK base image
FROM eclipse-temurin:17-jdk

# Set the working directory inside the container
WORKDIR /app

# Copy the Gradle wrapper and project files
COPY . .

# Grant permission to run Gradle wrapper
RUN chmod +x ./gradlew

# Build the application
RUN ./gradlew build

# Expose the port your Spring Boot app runs on
EXPOSE 8080

# Set the entry point to run your JAR
CMD ["java", "-jar", "build/libs/task-1.0-SNAPSHOT.jar"]
