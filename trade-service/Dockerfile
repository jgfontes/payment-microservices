FROM maven:3.9-eclipse-temurin-21

WORKDIR /app

COPY . .

RUN mvn clean package -DskipTests && \
    cp target/*.jar /app.jar

EXPOSE 8082

ENTRYPOINT ["java", "-jar", "/app.jar"]