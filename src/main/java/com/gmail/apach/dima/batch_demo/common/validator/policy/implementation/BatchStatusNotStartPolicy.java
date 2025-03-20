package com.gmail.apach.dima.batch_demo.common.validator.policy.implementation;

import com.gmail.apach.dima.batch_demo.common.constant.Error;
import com.gmail.apach.dima.batch_demo.common.validator.policy.definition.AbstractValidationPolicy;
import org.springframework.batch.core.BatchStatus;

public class BatchStatusNotStartPolicy extends AbstractValidationPolicy<BatchStatus> {

    private static final String ERROR_PARAM = "The previous job execution is still running";

    @Override
    public boolean satisfy(BatchStatus batchStatus) {
        return !BatchStatus.STARTED.equals(batchStatus) && !BatchStatus.STARTING.equals(batchStatus);
    }

    @Override
    public Error errorCode() {
        return Error.JOB_INTERRUPTED;
    }

    @Override
    public Object[] errorParams() {
        return new String[]{ERROR_PARAM};
    }
}
