FROM openjdk:11

EXPOSE 8080

ARG JAR_FILE=build/libs/*.jar

COPY ${JAR_FILE} /HOME/spring/app.jar

WORKDIR /HOME/spring/

CMD ["java", "-jar", "./app.jar"]