package com.example.helloworld.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

        @Bean
        public OpenAPI springShopOpenAPI() {

        return new OpenAPI()
                .info(new Info().title("Spring Boot Enterprise Boilerplate")
                        .description("REST API Documentation")
                                .version("1.0.0")
                                        .contact(new Contact()
                                                .name("Rafi Chandra")
                                                .email("your@email.com")
                                        )
                                        .license(new License().name("MIT")
                                )
                        )
                        .externalDocs(new ExternalDocumentation().description("Project Documentation")
                );
        }
}