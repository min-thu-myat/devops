FROM eclipse-temurin:21-jre
COPY ./target/seMethods.jar /tmp/
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "seMethods.jar"]