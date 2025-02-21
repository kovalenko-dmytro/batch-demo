package com.gmail.apach.dima.batch_demo.infrastructure.adapter.input.web.common.swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition
public class OpenApiConfig {

    @Bean
    public OpenAPI customizeOpenAPI() {
        return new OpenAPI()
            .info(info());
    }

    private Info info() {
        Info info = new Info();
        info.setTitle(OpenApi.OPEN_API_INFO_TITLE.getValue());
        return info;
    }
}
