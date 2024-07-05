package com.example.carDatabaseApplication;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    public OpenAPI carDatabaseOpenApi(){
        return new OpenAPI()
                .info(new Info()
                        .title("Car Rest API")
                        .description("My car Stock")
                        .version("1.0"));
    }

}
