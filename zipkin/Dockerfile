FROM openjdk:12
COPY /target/zipkin-0.0.1-SNAPSHOT.jar /tmp
EXPOSE 9411
ENTRYPOINT ["java", "-jar", "/tmp/zipkin-0.0.1-SNAPSHOT.jar"]