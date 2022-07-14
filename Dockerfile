FROM maven:3.6.0-jdk-11-slim AS TEMP_BUILD_IMAGE
ENV APP_HOME=/usr/app/
WORKDIR $APP_HOME
#COPY $PWD $APP_HOME
#COPY $PWD/backend/settings.gradle $APP_HOME
#COPY $PWD/backend/gradlew $APP_HOME
#COPY $PWD/backend/gradle $APP_HOME/gradle
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean install -DskipTests

FROM adoptopenjdk/openjdk11
ENV ARTIFACT_NAME=connector-0.0.1-SNAPSHOT.jar
ENV APP_HOME=/usr/app/
WORKDIR $APP_HOME
#COPY --from=TEMP_BUILD_IMAGE $APP_HOME/build/libs/$ARTIFACT_NAME .
COPY --from=TEMP_BUILD_IMAGE /home/app/target/connector-0.0.1-SNAPSHOT.jar /usr/local/lib/connector-0.0.1-SNAPSHOT.jar
EXPOSE 8080
CMD ["java", "-jar", "/usr/local/lib/connector-0.0.1-SNAPSHOT.jar"]