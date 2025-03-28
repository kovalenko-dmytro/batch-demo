package com.gmail.apach.dima.batch_demo.infrastructure.output.oss.config;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

@Configuration
@ConfigurationProperties(prefix = "spring.cloud.aws")
@Validated
@Data
public class AwsS3Properties {

    private S3 s3;

    @Data
    @Validated
    public static class S3 {

        @NotBlank
        private String bucket;
    }
}
