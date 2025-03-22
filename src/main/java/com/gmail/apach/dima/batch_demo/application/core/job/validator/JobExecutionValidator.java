package com.gmail.apach.dima.batch_demo.application.core.job.validator;

import com.gmail.apach.dima.batch_demo.common.util.MessageUtil;
import com.gmail.apach.dima.batch_demo.common.validator.policy.implementation.BatchStatusNotEqualsStartedPolicy;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.BatchStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
@RequiredArgsConstructor
public class JobExecutionValidator {

    private final MessageUtil messageUtil;

    public void checkNotStarted(BatchStatus batchStatus) {
        final var policy = new BatchStatusNotEqualsStartedPolicy();
        Assert.state(
            policy.satisfy(batchStatus),
            messageUtil.getMessage(policy.errorCode(), policy.errorParams()));
    }
}
