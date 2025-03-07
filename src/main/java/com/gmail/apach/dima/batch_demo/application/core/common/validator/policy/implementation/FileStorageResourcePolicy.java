package com.gmail.apach.dima.batch_demo.application.core.common.validator.policy.implementation;

import com.gmail.apach.dima.batch_demo.application.core.common.validator.policy.definition.AbstractValidationPolicy;
import com.gmail.apach.dima.batch_demo.application.core.job.model.RequestParameter;
import com.gmail.apach.dima.batch_demo.infrastructure.common.message.code.Error;
import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.NonNull;

public class FileStorageResourcePolicy extends AbstractValidationPolicy<String> {

    @Override
    public boolean satisfy(@NonNull String fileResource) {
        return StringUtils.isNoneBlank(fileResource);
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
