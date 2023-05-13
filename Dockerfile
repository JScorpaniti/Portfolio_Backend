FROM amazoncorretto:11-alpine-jdk
MAINTAINER JScorpaniti
COPY target/jms-0.0.1-SNAPSHOT.jar jms-app.jar
ENTRYPOINT ["java","-jar","/jms-app.jar"]
