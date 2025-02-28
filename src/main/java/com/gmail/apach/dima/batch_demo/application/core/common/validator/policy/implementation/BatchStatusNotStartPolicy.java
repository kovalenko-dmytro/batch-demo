package com.gmail.apach.dima.batch_demo.application.core.common.validator.policy.implementation;

import com.gmail.apach.dima.batch_demo.application.core.common.validator.policy.definition.AbstractValidationPolicy;
import com.gmail.apach.dima.batch_demo.infrastructure.common.message.code.Error;
import org.springframework.batch.core.BatchStatus;
import org.springframework.lang.NonNull;

public class BatchStatusNotStartPolicy extends AbstractValidationPolicy<BatchStatus> {

    @Override
    public boolean satisfy(@NonNull BatchStatus batchStatus) {
        return !BatchStatus.STARTED.equals(batchStatus) && !BatchStatus.STARTING.equals(batchStatus);
    }

    @Override
    public Error errorCode() {
        return Error.JOB_INTERRUPTED;
    }

    @Override
    public Object[] errorParams() {
        return null;
    }
}
