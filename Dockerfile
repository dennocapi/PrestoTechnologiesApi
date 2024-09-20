# Use the official Maven image to build the app
FROM maven:3.8.5-openjdk-17 AS builder
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Use the official OpenJDK image to run the app
FROM openjdk:17-jdk-slim
COPY --from=builder /app/target/prestoTech-0.0.1-SNAPSHOT.jar prestoTech.jar
ENTRYPOINT ["java", "-jar", "/prestoTech.jar"]
