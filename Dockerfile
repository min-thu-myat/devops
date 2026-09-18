FROM eclipse-temurin:21-jre
WORKDIR /tmp
COPY target/seMethods-0.1.0.2-jar-with-dependencies.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]