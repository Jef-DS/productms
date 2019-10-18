FROM openjdk:12
COPY /target/product-0.0.1-SNAPSHOT.jar /tmp
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "/tmp/product-0.0.1-SNAPSHOT.jar"]