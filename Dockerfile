# Build stage
FROM maven:3.8.6-eclipse-temurin-17 AS build
WORKDIR /workspace/app

# Copy project files
COPY pom.xml .
COPY src src

# Build application
RUN mvn clean package -DskipTests

# Runtime stage
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app

# Copy built JAR
COPY --from=build /workspace/app/target/librarymanagementsystem-*.jar app.jar

# Set environment variables (customize these for your DB configuration)
ENV SPRING_DATASOURCE_URL=jdbc:mysql://localhost:3306/library
ENV SPRING_DATASOURCE_USERNAME=root
ENV SPRING_DATASOURCE_PASSWORD=root

# Expose and run
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]