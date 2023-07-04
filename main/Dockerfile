

FROM maven:3-openjdk-17-slim as build
COPY target/main-0.0.1-SNAPSHOT.jar /
EXPOSE 8181
CMD exec java -jar main-0.0.1-SNAPSHOT.jar