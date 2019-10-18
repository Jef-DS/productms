FROM openjdk:12
COPY /target/zuul-0.0.1-SNAPSHOT.jar /tmp
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/tmp/zuul-0.0.1-SNAPSHOT.jar"]