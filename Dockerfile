# This is the docker-compose file that will be used to build the docker image
FROM openjdk:17-jdk-alpine
LABEL authors="Billy"

# Set the working directory to /app
WORKDIR /app

# Copy the current directory contents into the container at /app
COPY . /app

# Install any needed packages specified in requirements.txt
RUN gradlew clean build

# Make port 8080 available to the world outside this container
EXPOSE 8080

# Run app.py when the container launches
CMD ["java", "-jar", "./build/libs/hardtech_backend-0.0.1-SNAPSHOT.jar"]
