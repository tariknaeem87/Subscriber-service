FROM localhost:5000/demo-base-image
VOLUME /tmp
COPY config /config/
RUN ls -la /config/*
ADD subscriber-service-1.0.0-SNAPSHOT-exec.jar subscriber-service.jar
RUN bash -c 'touch /subscriber-service.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/subscriber-service.jar", "--spring.config.location=/config/deployment.docker.yml", "--logging.config=/config/logback-spring.xml"]