package com.userprofile.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    
    @Bean
    public OpenAPI userProfileOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("User Profile API")
                        .description("REST API for User Profile Management System")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("User Profile Team")
                                .email("support@userprofile.com")));
    }
}
