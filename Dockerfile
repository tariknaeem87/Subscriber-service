FROM openjdk:8-jdk-alpine
RUN mkdir -p /usr/app
COPY ./subscriber-service-1.0.0-SNAPSHOT-exec.jar /usr/app
WORKDIR /usr/app
EXPOSE 9999
ENTRYPOINT ["java", "-jar", "subscriber-service-1.0.0-SNAPSHOT-exec.jar"] 
