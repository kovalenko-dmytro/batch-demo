package com.gmail.apach.dima.batch_demo.manager.infrastructure.output.rest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestClient;

import java.time.Duration;

@Configuration
@SuppressWarnings("unused")
public class RestClientConfig {

    private static final int TIMEOUT = 3600000;

    @Bean
    public RestClient restClient() {
        return RestClient.builder()
            .requestFactory(requestFactory())
            .build();
    }

    @Bean
    ClientHttpRequestFactory requestFactory() {
        final var factory = new HttpComponentsClientHttpRequestFactory();
        factory.setConnectionRequestTimeout(Duration.ofMillis(TIMEOUT));
        factory.setReadTimeout(Duration.ofMillis(TIMEOUT));
        factory.setConnectTimeout(Duration.ofMillis(TIMEOUT));
        return factory;
    }
}
