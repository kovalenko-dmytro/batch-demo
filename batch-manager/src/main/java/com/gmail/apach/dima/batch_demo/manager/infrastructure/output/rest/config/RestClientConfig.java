package com.gmail.apach.dima.batch_demo.manager.infrastructure.output.rest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
@SuppressWarnings("unused")
public class RestClientConfig {

    @Bean
    public RestClient restClient() {
        return RestClient.builder()
            .build();
    }
}
