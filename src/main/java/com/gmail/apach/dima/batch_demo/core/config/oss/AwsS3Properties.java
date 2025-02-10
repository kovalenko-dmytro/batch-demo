package com.gmail.apach.dima.batch_demo.core.config.oss;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class AwsS3Properties {

    @NotBlank
    @Value("${spring.cloud.aws.s3.bucket}")
    private String bucket;
}
