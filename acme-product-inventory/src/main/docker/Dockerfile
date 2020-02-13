FROM openjdk:11-jre

COPY entrypoint.sh /
COPY app.conf /app/
COPY aspectjweaver.jar /app/
RUN chmod +x entrypoint.sh
COPY app.jar /app/
RUN chmod +x /app/app.jar

ENTRYPOINT ["./entrypoint.sh"]