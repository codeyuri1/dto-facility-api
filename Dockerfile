# Etapa de build
FROM gradle:8.7.0-jdk21 AS build

WORKDIR /app

COPY . .

RUN gradle clean build --no-daemon

# Etapa de runtime
FROM eclipse-temurin:21-jdk AS runtime

WORKDIR /app

# Copia o JAR gerado na etapa anterior
COPY --from=build /app/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
