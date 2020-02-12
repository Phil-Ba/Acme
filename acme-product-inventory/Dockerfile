FROM openjdk:11-jre-slim

COPY target/app.jar /app/
COPY src/main/docker/entrypoint.sh /
RUN chmod +x entrypoint.sh
RUN chmod +x /app/app.jar

ENTRYPOINT ["./entrypoint.sh"]