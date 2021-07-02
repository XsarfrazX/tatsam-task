FROM openjdk:8-jdk-alpine
MAINTAINER Sarfraz
COPY target/priority-0.0.1.jar priority-0.0.1.jar
RUN ls
ENTRYPOINT ["java","-jar","/priority-0.0.1.jar"]