package com.gmail.apach.dima.batch_demo.common.config.message;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.FixedLocaleResolver;

import java.util.Locale;

@Configuration
@RequiredArgsConstructor
@SuppressWarnings("unused")
public class MessageConfig {

    private final MessageConfigProperties properties;

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames(properties.getBasename());
        messageSource.setDefaultEncoding(properties.getEncoding());
        messageSource.setDefaultLocale(properties.getDefaultLocale());
        messageSource.setCacheSeconds(properties.getCacheDuration());
        return messageSource;
    }

    @Bean
    public LocaleResolver localeResolver() {
        FixedLocaleResolver localeResolver = new FixedLocaleResolver();
        localeResolver.setDefaultLocale(Locale.ENGLISH);
        return localeResolver;
    }
}
