FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
# Copie du code source et du fichier de configuration Maven
COPY pom.xml .
COPY src ./src
# Compilation et empaquetage (ignore les tests )
RUN mvn clean package -DskipTests
#RUN mvn clean package

FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
CMD ["java", "-jar", "/app/app.jar"]
EXPOSE 9090
