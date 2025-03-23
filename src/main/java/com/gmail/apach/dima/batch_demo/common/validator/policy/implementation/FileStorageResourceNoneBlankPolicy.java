package com.gmail.apach.dima.batch_demo.common.validator.policy.implementation;

import com.gmail.apach.dima.batch_demo.application.core.job.model.RequestParameter;
import com.gmail.apach.dima.batch_demo.common.constant.Error;
import com.gmail.apach.dima.batch_demo.common.validator.policy.definition.AbstractValidationPolicy;
import org.apache.commons.lang3.StringUtils;

public class FileStorageResourceNoneBlankPolicy extends AbstractValidationPolicy<String> {

    @Override
    public boolean satisfy(String fileResource) {
        return StringUtils.isNoneBlank(fileResource);
    }

    @Override
    public Error errorCode() {
        return Error.FILE_STORAGE_RESOURCE_MISSING;
    }

    @Override
    public Object[] errorParams() {
        return new Object[]{RequestParameter.FILE_STORAGE_RESOURCE.getName()};
    }
}
