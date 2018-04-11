FROM java:8
ADD target/springboot-docker.jar spring-docker.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","spring-docker.jar"]
