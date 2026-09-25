FROM eclipse-temurin:21-jre

WORKDIR /tmp

# Using a wildcard *-jar-with-dependencies.jar prevents you from having
# to update the Dockerfile every time you change your version in pom.xml!
COPY target/*-jar-with-dependencies.jar /tmp/seMethods.jar

ENTRYPOINT ["java", "-jar", "/tmp/seMethods.jar"]