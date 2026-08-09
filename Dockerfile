# Fase 1: Build
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN ./mvnw clean package -DskipTests

# Fase 2: Run
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]

#sirve como un manual de instrucciones paso a paso para que los servidores de Render entiendan cómo preparar el entorno de Java, compilar tu proyecto de Spring Boot y ponerlo a correr.
