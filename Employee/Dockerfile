FROM amazoncorretto:17-alpine
ADD target/employee.jar employee.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/employee.jar"]