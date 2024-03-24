FROM gradle:latest as builder
WORKDIR /app
COPY build.gradle settings.gradle /app/
COPY src /app/src
RUN gradle build

# Этап создания финального Docker образа с использованием JRE
FROM openjdk:17-jdk-alpine
COPY --from=builder /app/build/libs/books-top-ten.jar /app/books-top-ten.jar
CMD ["java", "-jar", "/app/books-top-ten.jar"]
