FROM openjdk:17-jdk-slim
COPY target/*.jar /Task_Management_System.jar
ENTRYPOINT ["java", "-jar", "/Task_Management_System.jar"]