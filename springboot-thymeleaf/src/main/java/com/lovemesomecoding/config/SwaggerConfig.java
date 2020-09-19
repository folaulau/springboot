package com.lovemesomecoding.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Slf4j
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        log.info("setting up Docket..");
        Docket docket = new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.basePackage("com.lovemesomecoding")).paths(PathSelectors.any()).build().apiInfo(getApiInfo());

        log.info("docket setup!");

        return docket;
    }

    private ApiInfo getApiInfo() {
        Contact contact = new Contact("Folau Kaveinga", "folaukaveinga.org", "folaukaveinga@gmail.com");
        return new ApiInfoBuilder().title("Spring Boot Tutorial").description("Spring Boot API").version("1.0.0").contact(contact).build();
    }
}