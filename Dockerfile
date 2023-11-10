FROM openjdk:11-jre

COPY build/libs/*.jar app.jar

ENTRYPOINT ["nohup","java", "-jar", "app.jar", "-Dspring.profiles.active=prod"]