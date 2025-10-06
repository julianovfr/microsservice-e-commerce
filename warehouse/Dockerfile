FROM maven:3.8.4-openjdk-17 AS build

COPY src /app/src
COPY pom.xml /app

WORKDIR /app
RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-slim

WORKDIR /app

COPY  --from=build /app/target/warehouse-0.0.1-SNAPSHOT.jar /app/app.jar

EXPOSE 8082

CMD ["java", "-jar", "app.jar"]


