FROM java:8
ADD target/springboot-docker.jar spring-docker.jar
RUN bash -c 'spring-docker /spring-docker.jar'
EXPOSE 8082
ENTRYPOINT["java","-jar","spring-docker.jar"]