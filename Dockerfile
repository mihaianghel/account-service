FROM openjdk:8-alpine

WORKDIR /

COPY build/libs/account-service-0.0.1-SNAPSHOT.jar /

EXPOSE 8080

CMD java -Djava.security.egd=file:/dev/./urandom -jar  ./account-service-0.0.1-SNAPSHOT.jar
