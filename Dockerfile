FROM maven:3.8.4-openjdk-17-slim
RUN mkdir /root/backend
COPY ./target/*.jar /root/backend
WORKDIR /root/backend
RUN mv /root/backend/*.jar /root/backend/app.jar
ENTRYPOINT ["java","-jar","target/app.jar"]