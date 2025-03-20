package com.gmail.apach.dima.batch_demo.application.core.job.validator;

import com.gmail.apach.dima.batch_demo.common.util.MessageUtil;
import com.gmail.apach.dima.batch_demo.common.validator.policy.implementation.BatchStatusNotStartPolicy;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobInterruptedException;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JobStatusValidator {

    private final JobExplorer jobExplorer;
    private final MessageUtil messageUtil;

    public void checkStatus(String jobName) throws Exception {
        final var batchStatus = Optional
            .ofNullable(jobName)
            .map(job -> jobExplorer.getLastJobInstance(jobName))
            .map(jobExplorer::getLastJobExecution)
            .map(JobExecution::getStatus)
            .orElse(BatchStatus.UNKNOWN);

        final var policy = new BatchStatusNotStartPolicy();
        try {
            Assert.state(
                policy.satisfy(batchStatus),
                messageUtil.getMessage(policy.errorCode(), policy.errorParams()));
        } catch (IllegalStateException e) {
            throw new JobInterruptedException(e.getMessage(), BatchStatus.FAILED);
        }
    }
}
