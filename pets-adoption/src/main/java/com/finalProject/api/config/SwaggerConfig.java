package com.finalProject.api.config;

import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.service.Contact;

public class SwaggerConfig {
	
	@Bean
	Docket createApiDoc() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.finalProject.api.model"))
				.paths(PathSelectors.any())
				.build();
		
	}
	

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("Documentacion de la franquicia Pet Adoption")
				.description("informacion sobre la API REST para consumo de clientes")
				.termsOfServiceUrl("")
				.contact(new Contact("Jose Acacio", "www.josedavid.com", "jdacaciob@gmail.com"))
				.version("1.0")
				.build();
					
	}

}
