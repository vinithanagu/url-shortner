package com.example.url.shortner.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguration {
  @Bean
  public OpenAPI openAPI() {
    return new OpenAPI().info(new Info().title("Url Shorten Service - API")
        .description("Endpoints to convert Long-url to short-url and vice-versa")
        .version("v1.0.0")
        .license(new License().name("Apache 2.0").url("http://springdoc.org")));
  }
}
