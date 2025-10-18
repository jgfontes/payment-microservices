package com.jgfontes.recepy.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI tradingServiceOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                    .title("Trading Service API")
                    .description("API for executing and retrieving trades")
                    .version("v1.0"))
                .externalDocs(new ExternalDocumentation()
                    .description("Trading Service Repository")
                    .url("https://github.com/jgfontes/Recepy"));
    }
}
