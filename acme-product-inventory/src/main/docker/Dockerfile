FROM openjdk:11-jre

COPY app.jar /app/
COPY entrypoint.sh /
COPY app.conf /app/
COPY aspectjweaver.jar /app/
RUN chmod +x entrypoint.sh
RUN chmod +x /app/app.jar

ENTRYPOINT ["./entrypoint.sh"]