package com.folaukaveinga.swagger.config;

import javax.servlet.ServletContext;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.PathProvider;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.paths.RelativePathProvider;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	@Bean
	public Docket api(ServletContext servletContext) {
		return new Docket(DocumentationType.SWAGGER_2)
				.select().apis(RequestHandlerSelectors.basePackage("com.folaukaveinga.swagger.rest"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(getApiInfo());
	}
	private ApiInfo getApiInfo() {
        Contact contact = new Contact("Folau Kaveinga", "http://lovemesomecoding.com", "folaukaveinga@gmail.com");
        return new ApiInfoBuilder()
                .title("Springboot Rest API")
                .description("Example API")
                .version("1.0.0")
                .license("Apache 2.0")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0")
                .contact(contact)
                .build();
    }
}
