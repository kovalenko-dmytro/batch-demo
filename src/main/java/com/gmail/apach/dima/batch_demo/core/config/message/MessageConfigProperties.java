package com.gmail.apach.dima.batch_demo.core.config.message;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.Locale;

@Configuration
@Data
public class MessageConfigProperties {

    @NotBlank
    @Value("${spring.messages.basename}")
    private String basename;

    @NotBlank
    @Value("${spring.messages.encoding}")
    private String encoding;

    @NotBlank
    @Value("${spring.messages.cache-duration}")
    private int cacheDuration;

    private Locale defaultLocale = Locale.ENGLISH;
}
