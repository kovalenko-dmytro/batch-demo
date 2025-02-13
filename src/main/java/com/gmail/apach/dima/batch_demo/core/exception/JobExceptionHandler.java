package com.gmail.apach.dima.batch_demo.core.exception;

import com.gmail.apach.dima.batch_demo.core.service.message.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobInterruptedException;
import org.springframework.batch.repeat.RepeatContext;
import org.springframework.batch.repeat.exception.ExceptionHandler;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class JobExceptionHandler implements ExceptionHandler {

    private final MessageService messageService;
    @Override
    public void handleException(@NonNull RepeatContext context, @NonNull Throwable throwable) throws Throwable {
        throw new JobInterruptedException("Aborting Job", BatchStatus.FAILED);
    }
}
