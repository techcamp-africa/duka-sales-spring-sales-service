FROM java:8-jdk-alpine

COPY /target/sales-0.0.1-SNAPSHOT.jar /usr/app/

WORKDIR /usr/app

RUN sh -c 'touch sales-service-0.0.1-SNAPSHOT.jar'

EXPOSE 8080

ENTRYPOINT ["java","-jar","sales-0.0.1-SNAPSHOT.jar"]