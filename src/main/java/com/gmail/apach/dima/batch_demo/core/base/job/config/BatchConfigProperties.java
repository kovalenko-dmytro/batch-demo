package com.gmail.apach.dima.batch_demo.core.base.job.config;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

@Configuration
@ConfigurationProperties(prefix = "spring.jpa.properties.hibernate.jdbc")
@Validated
@Data
public class BatchConfigProperties {

    @NotNull
    private Integer batchSize;
}
