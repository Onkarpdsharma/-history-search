FROM  openjdk:11
VOLUME /tmp
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} history.jar
ENTRYPOINT [ "java", "-jar", "history.jar" ]
EXPOSE 8080