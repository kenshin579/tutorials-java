FROM openjdk:8-jdk-alpine as build
WORKDIR /app

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src

RUN ./mvnw package -DskipTests

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

FROM openjdk:8-jre-alpine
WORKDIR /app
COPY --from=build /app/app.jar /app
CMD ["java","-jar","/app/app.jar"]
