# This is the docker-compose file that will be used to build the docker image
FROM amazoncorretto:17-alpine-jdk
LABEL authors="Billy"

# Create a directory
WORKDIR /app

# Copy all the files from the current directory to the image
COPY . .

# build the project avoiding tests
RUN ./gradlew clean build -x test

# Expose port 8080
EXPOSE 8080

# Run the jar file
CMD ["java", "-jar", "build/libs/hardtech_backend-0.0.1-SNAPSHOT.jar"]
