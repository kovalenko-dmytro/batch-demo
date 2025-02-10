package com.gmail.apach.dima.batch_demo.core.service.message;

import com.gmail.apach.dima.batch_demo.core.config.message.constant.Error;
import com.gmail.apach.dima.batch_demo.core.config.message.constant.Info;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MessageService {

    private final MessageSource messageSource;

    public String message(Error error, Object... args) {
        return messageSource.getMessage(error.getKey(), args, LocaleContextHolder.getLocale());
    }

    public String message(Info info, Object... args) {
        return messageSource.getMessage(info.getKey(), args, LocaleContextHolder.getLocale());
    }
}
