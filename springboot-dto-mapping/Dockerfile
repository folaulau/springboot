FROM java:8
ADD target/springboot-dto-mapping.jar springboot-dto-mapping.jar
EXPOSE 8080
ENTRYPOINT ["java", "-Dspring.profiles.active=local", "-jar","springboot-dto-mapping.jar"]