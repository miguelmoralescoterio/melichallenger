FROM openjdk:11-jdk
LABEL maintainer="miguelmoralescoterio@gmail.com"
VOLUME /main-app
ADD businessdomain/users/target/users-1.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar","/app.jar"]