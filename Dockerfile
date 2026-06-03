# Build stage
FROM maven:3.9.16-openjdk-21 AS builder
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package -DskipTests

# Runtime stage
FROM openjdk:21-jre-slim
WORKDIR /app
COPY --from=builder /app/target/*.jar cms-api.jar
EXPOSE 9090
ENTRYPOINT ["java", "-jar", "cms-api.jar"]
