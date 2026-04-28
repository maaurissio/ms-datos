FROM maven:3.9.9-eclipse-temurin-21 AS build

WORKDIR /workspace

COPY pom.xml ./
RUN mvn -q -DskipTests dependency:go-offline

COPY src ./src
RUN mvn -DskipTests package

FROM eclipse-temurin:21-jre

WORKDIR /work

COPY --from=build /workspace/target/quarkus-app/ /work/quarkus-app/

EXPOSE 8080

ENV JAVA_OPTS=""

CMD ["java", "-jar", "/work/quarkus-app/quarkus-run.jar"]
