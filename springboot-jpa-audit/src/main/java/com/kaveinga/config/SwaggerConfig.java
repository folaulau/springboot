package com.kaveinga.config;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    private Logger      log = LoggerFactory.getLogger(this.getClass());

    @Bean
    public Docket api() {
        log.info("setting up Docket..");
        Docket docket = new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("com.kaveinga"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(getApiInfo());

        log.info("docket setup!");
        return docket;
    }

    private ApiInfo getApiInfo() {
        Contact contact = new Contact("Ramesh L. Nori", "www.sidecarhealth.com", "rnori@sidecarhealth.com");
        return new ApiInfoBuilder().title("Sidecar Health Doctor Rest API").description("Doctors API").version("1.0.0").contact(contact).build();
    }

}
