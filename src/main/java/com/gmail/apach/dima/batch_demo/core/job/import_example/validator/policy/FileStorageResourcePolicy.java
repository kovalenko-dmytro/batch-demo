package com.gmail.apach.dima.batch_demo.core.job.import_example.validator.policy;

import com.gmail.apach.dima.batch_demo.core.base.common.validator.policy.AbstractValidationPolicy;
import com.gmail.apach.dima.batch_demo.core.base.model.job.Parameter;
import com.gmail.apach.dima.batch_demo.core.base.model.job.RequestParameters;
import com.gmail.apach.dima.batch_demo.infrastructure.common.message.code.Error;
import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.NonNull;

public class FileStorageResourcePolicy extends AbstractValidationPolicy<RequestParameters> {

    @Override
    public boolean satisfy(@NonNull RequestParameters input) {
        return StringUtils.isNoneBlank(input.get(Parameter.FILE_STORAGE_RESOURCE));
    }

    @Override
    public Error errorCode() {
        return Error.FILE_STORAGE_RESOURCE_MISSING;
    }

    @Override
    public Object[] errorParams() {
        return new Object[]{Parameter.FILE_STORAGE_RESOURCE.getArg()};
    }
}
