FROM openjdk:17-alpine
WORKDIR /opt
ENV PORT 8282
EXPOSE 8282
COPY target/*.jar /opt/smartsalesmanager-image.jar
ENTRYPOINT exec java $JAVA_OPTS -jar smartsalesmanager-image.jar