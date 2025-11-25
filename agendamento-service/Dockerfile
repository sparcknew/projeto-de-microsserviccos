# ========== Build Stage ==========
FROM maven:3.9.9-eclipse-temurin-17 AS build
WORKDIR /app

# Copia pom.xml e baixa dependências (cache)
COPY pom.xml .
RUN mvn -q -B dependency:go-offline

# Copia o código-fonte e constrói o jar
COPY src ./src
RUN mvn -q -B package -DskipTests

# ========== Run Stage ==========
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Copia o jar gerado na etapa anterior
COPY --from=build /app/target/*.jar app.jar

# Porta padrão (cada serviço pode usar outra porta se precisar)
EXPOSE 8080

# Comando para iniciar a aplicação
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
