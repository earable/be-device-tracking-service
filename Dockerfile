FROM maven:3.8.4-openjdk-11-slim as build
WORKDIR /usr/src/app
COPY pom.xml /usr/src/app
COPY . .
RUN cd be-parent && \
    mvn clean package install -DskipTests && \
    cd .. && \
    cd be-common && \
    mvn clean package install -DskipTests && \
    cd .. && \
    mvn clean package install -DskipTests
EXPOSE 80
FROM adoptopenjdk/openjdk11:jre-11.0.6_10-alpine
COPY --from=build /usr/src/app/target/be-device-tracking-service-*.jar ./be-device-tracking-service.jar
CMD java $JAVA_OPTIONS -jar be-device-tracking-service.jar
