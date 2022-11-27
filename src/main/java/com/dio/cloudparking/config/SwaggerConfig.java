package com.dio.cloudparking.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;


@Component
public class SwaggerConfig {
    
  @Bean
  public OpenAPI customOpenAPI() {

    return new OpenAPI()
        .info(apiInfo());

  }

  private Info apiInfo(){
      return new Info()
      .title("Cloud Parking")
      .description("Spring Boot REST API para Estacionamento")
      .version("1.1.0");
  }
 
}
