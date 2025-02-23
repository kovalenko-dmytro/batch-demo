package com.gmail.apach.dima.batch_demo.application.core.job.listener;

import com.gmail.apach.dima.batch_demo.infrastructure.common.message.MessageUtil;
import com.gmail.apach.dima.batch_demo.infrastructure.common.message.code.Error;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
@SuppressWarnings("unused")
public class LogJobFailuresListener implements JobExecutionListener {

    private final MessageUtil messageUtil;

    @Override
    public void afterJob(JobExecution jobExecution) {
        final var allFailureExceptions = jobExecution.getAllFailureExceptions();
        if (CollectionUtils.isNotEmpty(allFailureExceptions)) {
            jobExecution.setStatus(BatchStatus.FAILED);
            final var errorMessages = allFailureExceptions.stream().map(Throwable::getMessage).toList();
            log.error(messageUtil.getMessage(Error.JOB_INTERRUPTED, errorMessages));
        }
    }
}
