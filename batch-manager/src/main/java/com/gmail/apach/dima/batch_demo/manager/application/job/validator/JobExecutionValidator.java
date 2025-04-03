package com.gmail.apach.dima.batch_demo.manager.application.job.validator;

import com.gmail.apach.dima.batch_demo.common.constant.Error;
import com.gmail.apach.dima.batch_demo.common.exception.ValidationException;
import com.gmail.apach.dima.batch_demo.common.model.RequestParameter;
import com.gmail.apach.dima.batch_demo.common.model.RequestParameters;
import com.gmail.apach.dima.batch_demo.common.util.MessageUtil;
import com.gmail.apach.dima.batch_demo.common.validator.policy.implementation.BatchStatusNotEqualsStartedPolicy;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.List;

@Component
@RequiredArgsConstructor
public class JobExecutionValidator {

    private final MessageUtil messageUtil;

    public void checkNotStarted(BatchStatus batchStatus) {
        final var policy = new BatchStatusNotEqualsStartedPolicy();
        try {
            Assert.state(
                policy.satisfy(batchStatus),
                messageUtil.getMessage(policy.errorCode(), policy.errorParams()));
        } catch (IllegalStateException e) {
            throw new ValidationException(e.getMessage());
        }
    }

    public void checkUniqueParameters(List<JobExecution> jobExecutions, RequestParameters parameters) {
        final var marker = RequestParameter.JOB_EXECUTION_MARKER;
        final var markerNotExists = jobExecutions.stream()
            .map(JobExecution::getJobParameters)
            .noneMatch(jobParams -> parameters.get(marker).contentEquals(jobParams.getString(marker.getName())));
        try {
            Assert.state(
                jobExecutions.isEmpty() || markerNotExists,
                messageUtil.getMessage(
                    Error.JOB_EXECUTION_ALREADY_EXISTS, parameters.get(marker)));
        } catch (IllegalStateException e) {
            throw new ValidationException(e.getMessage());
        }
    }
}
