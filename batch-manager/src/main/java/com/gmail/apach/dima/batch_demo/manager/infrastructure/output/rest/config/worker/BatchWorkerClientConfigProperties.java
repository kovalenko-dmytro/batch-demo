package com.gmail.apach.dima.batch_demo.manager.infrastructure.output.rest.config.worker;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

@Configuration
@ConfigurationProperties(prefix = "client.api.batch-worker")
@Validated
@Data
public class BatchWorkerClientConfigProperties {

    @NotBlank
    private String host;
    @NotBlank
    private String port;
    @NotBlank
    private String contextPath;

    private UriPath uriPath;

    @Data
    @Validated
    public static class UriPath {

        @NotBlank
        private String executeJob;
    }
}
