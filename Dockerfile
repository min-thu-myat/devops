FROM eclipse-temurin:21-jre

WORKDIR /tmp

# Copy the fixed-name JAR produced by the updated pom.xml
COPY ./target/seMethods.jar /tmp/seMethods.jar

# Pass database location and delay as arguments
ENTRYPOINT ["java", "-jar", "seMethods.jar", "db:3306", "10000"]