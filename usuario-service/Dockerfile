# ------------ STAGE 1: BUILD -----------------
FROM maven:3.9.6-eclipse-temurin-17 AS build

WORKDIR /build

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests


# ------------ STAGE 2: RUNTIME -----------------
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

COPY --from=build /build/target/agendamento-service-1.0.0.jar app.jar

ENV JAVA_OPTS="-XX:+UseContainerSupport -XX:MaxRAMPercentage=75"

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
