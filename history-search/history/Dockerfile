FROM  openjdk:17
VOLUME /app
ARG JAR_FILE=target/*.jar
#COPY ./app/main/resources/log4j2.xml ./*
COPY ${JAR_FILE} history.jar
ENTRYPOINT [ "java", "-jar", "history.jar" ]
EXPOSE 8080