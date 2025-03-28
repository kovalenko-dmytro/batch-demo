package com.gmail.apach.dima.batch_demo.common.util;

import com.gmail.apach.dima.batch_demo.common.constant.Error;
import com.gmail.apach.dima.batch_demo.common.constant.Info;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MessageUtil {

    private final MessageSource messageSource;

    public String getMessage(Error error, Object... args) {
        return messageSource.getMessage(error.getKey(), args, LocaleContextHolder.getLocale());
    }


    public String getMessage(Info info, Object... args) {
        return messageSource.getMessage(info.getKey(), args, LocaleContextHolder.getLocale());
    }
}
