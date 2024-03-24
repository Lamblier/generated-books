FROM openjdk:17-jdk-alpine
COPY build/libs/books-top-ten.jar /books-top-ten.jar
ENTRYPOINT ["java", "-jar", "/books-top-ten.jar"]