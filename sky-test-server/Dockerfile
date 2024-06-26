FROM bellsoft/liberica-openjdk-alpine:21

WORKDIR /usr/sky-test/app

COPY target/*.jar app.jar

CMD ["java", "-jar", "app.jar"]
