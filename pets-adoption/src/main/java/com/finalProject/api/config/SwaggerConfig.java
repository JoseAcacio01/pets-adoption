package com.finalProject.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
@EnableWebMvc
public class SwaggerConfig {
    @Bean
    OpenAPI springShopOpenAPI() {
		return new OpenAPI()
				.info(new Info().title("Documentacion de la franquicia Pet Adoption")
						.description("Informacion sobre la API REST para consumo de clientes").version("v1.0.0")
						.contact(new Contact().name("Jose Acacio").email("jdacaciob@gmail.com").url("www.josedavid.com")));				
	}
}
