package com.gmail.apach.dima.batch_demo.application.core.common.validator.policy.implementation;

import com.gmail.apach.dima.batch_demo.application.core.common.validator.policy.definition.AbstractValidationPolicy;
import com.gmail.apach.dima.batch_demo.application.core.job.model.RequestParameter;
import com.gmail.apach.dima.batch_demo.infrastructure.common.message.code.Error;
import org.apache.commons.lang3.StringUtils;
import org.springframework.batch.core.JobParameters;
import org.springframework.lang.NonNull;

public class FileStorageResourcePolicy extends AbstractValidationPolicy<JobParameters> {

    @Override
    public boolean satisfy(@NonNull JobParameters parameters) {
        final var resource = (String) parameters
            .getParameter(RequestParameter.FILE_STORAGE_RESOURCE.getArg())
            .getValue();
        return StringUtils.isNoneBlank(resource);
    }

    @Override
    public Error errorCode() {
        return Error.FILE_STORAGE_RESOURCE_MISSING;
    }

    @Override
    public Object[] errorParams() {
        return new Object[]{RequestParameter.FILE_STORAGE_RESOURCE.getArg()};
    }
}
