FROM openjdk:12
COPY /target/eureka-0.0.1-SNAPSHOT.jar /tmp
EXPOSE 8761
ENTRYPOINT ["java", "-jar", "/tmp/eureka-0.0.1-SNAPSHOT.jar"]