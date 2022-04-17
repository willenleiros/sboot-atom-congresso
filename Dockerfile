FROM openjdk:17
RUN mkdir /root/backend
COPY ./target/*.jar /root/backend
WORKDIR /root/backend
RUN mv /root/backend/*.jar /root/backend/app.jar
ENTRYPOINT ["java","-jar","/root/backend/app.jar"]