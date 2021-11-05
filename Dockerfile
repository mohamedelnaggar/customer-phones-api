FROM openjdk:8-jdk-alpine
COPY ./target/customer-phones-api-1.0-SNAPSHOT.jar customer-phones-api-1.0-SNAPSHOT.jar
COPY ./target/classes/sample.db sample.db
ENTRYPOINT ["java","-jar","customer-phones-api-1.0-SNAPSHOT.jar"]