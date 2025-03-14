package com.gmail.apach.dima.batch_demo.common.config.message;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import java.util.Locale;

@Configuration
@ConfigurationProperties(prefix = "spring.messages")
@Validated
@Data
public class MessageConfigProperties {

    @NotBlank
    private String basename;

    @NotBlank
    private String encoding;

    @NotNull
    private Integer cacheDuration;

    private Locale defaultLocale = Locale.ENGLISH;
}
