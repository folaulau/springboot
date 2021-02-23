FROM java:8

ADD target/springboot-with-hasura.jar springboot-with-hasura.jar
EXPOSE 8898

ENTRYPOINT ["java","-jar", "springboot-with-hasura.jar"]