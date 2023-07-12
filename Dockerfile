FROM openjdk:17-jdk-alpine
ARG JAR_FILE=target/lecture-evaluation-dev-0.0.1-SNAPSHOT.jar
WORKDIR /app
COPY ${JAR_FILE} myboot.jar
ENTRYPOINT ["java","-jar","/myboot.jar"]
