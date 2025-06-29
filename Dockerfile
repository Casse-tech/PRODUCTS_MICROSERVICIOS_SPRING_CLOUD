FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
ARG JAR_FILE=target/PRODUCTS_MICROSERVICIOS_SPRING_CLOUD-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} msvc_products.jar
EXPOSE 8001
CMD ["java", "-jar", "msvc_products.jar"]