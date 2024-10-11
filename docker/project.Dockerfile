FROM maven:3.9.7-eclipse-temurin-21 AS builder
WORKDIR /opt/app
COPY ./pom.xml .
COPY .git/ .git/
COPY src /opt/app/src
RUN mvn dependency:go-offline
RUN mvn clean install

FROM eclipse-temurin:21.0.2_13-jre-jammy AS final
WORKDIR /opt/app
EXPOSE 8080
COPY --from=builder /opt/app/target/*.jar /opt/app/*.jar
ENTRYPOINT ["java", "-jar", "/opt/app/*.jar"]
