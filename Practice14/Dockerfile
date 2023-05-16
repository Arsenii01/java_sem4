FROM openjdk:19
FROM maven:3.8.1

WORKDIR /app
COPY . .
RUN mvn clean package

EXPOSE 80
CMD ["java", "-jar", "/app/target/Practice14-0.0.1-SNAPSHOT.jar"]