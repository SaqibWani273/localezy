FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean install
FROM openjdk:17-jdk-alpine
WORKDIR /app

COPY --from=build /app/target/aws-deploy-0.0.1-SNAPSHOT.jar ./app.jar
EXPOSE 8080
ENV SPRING_MAIL_SECRET_PW=temp
ENV ADMIN_PASSWORD=temp
ENV JWT_SECRET_KEY=temp
CMD ["java","-jar","app.jar"]

#FROM openjdk:17-jdk-alpine
#WORKDIR /app
#COPY target/aws-deploy-0.0.1-SNAPSHOT.jar app.jar
#EXPOSE 8080
## Replace with your application port if different
#ENTRYPOINT ["java", "-jar", "/app.jar"]
##
#
## ARG JAR_FILE=target/*.jar
## COPY ${JAR_FILE} app.jar
## ENTRYPOINT ["java","-jar","/app.jar"]
#These environment variables should be passed at build time

FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean install
FROM openjdk:17-jdk-alpine
WORKDIR /app
#ENV LOCALEZY_RDS_PW=SAQIB123456789
COPY --from=build /app/target/localezy-0.0.1-SNAPSHOT.jar ./app.jar
#COPY /target/aws-deploy-0.0.1-SNAPSHOT.jar ./app.jar
EXPOSE 8080

ENV JWT_SECRET_KEY=TEMP
ENV ADMIN_PASSWORD=TEMP
ENV SPRING_MAIL_SECRET_PW=TEMP
CMD ["java","-jar","app.jar"]

