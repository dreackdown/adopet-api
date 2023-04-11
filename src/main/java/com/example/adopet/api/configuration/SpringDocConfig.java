package com.example.adopet.api.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Adopet API")
                        .description("API Rest da aplicação Adopet, contendo as funcionalidades de CRUD de Tutores, Abrigos e Pets, além da funcionalidade de adotar um pet")
                        .contact(new Contact()
                                .name("Time Backend")
                                .email("backend@adopet.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://adopet/api/licenca")));
    }
}
