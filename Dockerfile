FROM openjdk:22
MAINTAINER shounakbhalerao777@gmail.com

#ENV MYSQL_USER "root"
#ENV MYSQL_PASSWORD "root"
ENV MYSQL_URL "jdbc:mysql://mysql_container:3308/user_db?useSSL=false&allowMultiQueries=true&serverTimezone=UTC&allowPublicKeyRetrieval=true"

COPY target/grocery-booking-api-0.0.1-SNAPSHOT.jar grocery-booking-api-0.0.1-SNAPSHOT.jar
EXPOSE 8080

ENTRYPOINT ["java","-jar","/grocery-booking-api-0.0.1-SNAPSHOT.jar"]