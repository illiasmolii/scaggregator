FROM openjdk
VOLUME /tmp
EXPOSE 8080
ADD /target/scaaggregator-0.0.1-SNAPSHOT.jar scaaggregator.jar
ENTRYPOINT ["java", "-jar", "scaaggregator.jar"]