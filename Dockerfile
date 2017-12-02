FROM openjdk:8-jre-alpine

RUN mkdir /app

WORKDIR /app

ADD ./target/apartments-2.5.0-SNAPSHOT.jar /app

EXPOSE 8081

CMD ["java", "-jar", "apartments-2.5.0-SNAPSHOT.jar"]