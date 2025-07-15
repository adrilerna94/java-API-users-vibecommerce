package com.adriauson.vibecommerce.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("VibeCommerce - Users API")
                        .version("1.0.0")
                        .description("This API handles user registration, retrieval, updating and deletion in the VibeCommerce application."));
    }
}
