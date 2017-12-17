#!/bin/sh

cd ../../../..
mvn -Dserver.port=8081 \
-Dspring.datasource.driverClassName=com.mysql.jdbc.Driver \
-Dspring.datasource.url=jdbc:mysql://127.0.0.1:3306/tjz?zeroDateTimeBehavior=convertToNull \
-Dspring.datasource.username=tjz \
-Dspring.datasource.password=123 \
spring-boot:run 
