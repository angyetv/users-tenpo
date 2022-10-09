FROM adoptopenjdk:11-jre-hotspot
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} users-tenpo-1.0.jar
ENTRYPOINT ["java", "-jar", "/users-tenpo-1.0.jar"]