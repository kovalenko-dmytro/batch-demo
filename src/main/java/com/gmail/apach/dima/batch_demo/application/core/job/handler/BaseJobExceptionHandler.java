package com.gmail.apach.dima.batch_demo.application.core.job.handler;

import com.gmail.apach.dima.batch_demo.infrastructure.common.message.MessageUtil;
import com.gmail.apach.dima.batch_demo.infrastructure.common.message.code.Error;
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
public class BaseJobExceptionHandler implements ExceptionHandler {

    private final MessageUtil messageUtil;

    @Override
    public void handleException(@NonNull RepeatContext context, @NonNull Throwable throwable) throws Throwable {
        throw new JobInterruptedException(
            messageUtil.getMessage(Error.JOB_INTERRUPTED, throwable.getMessage()), BatchStatus.FAILED);
    }
}
