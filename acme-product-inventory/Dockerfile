FROM openjdk:11

COPY target/app.jar /app/
COPY src/main/docker/entrypoint.sh /
RUN chmod +x entrypoint.sh

ENTRYPOINT ["./entrypoint.sh"]