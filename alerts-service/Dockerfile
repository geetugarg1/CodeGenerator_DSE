FROM java:8-jre

USER root

ADD ./target/alerts-service-0.1-SNAPSHOT.jar /opt/alerts-service-0.1-SNAPSHOT.jar
CMD ["java", "-Xmx200m", "-jar", "/opt/alerts-service-0.1-SNAPSHOT.jar"]

EXPOSE 8080

