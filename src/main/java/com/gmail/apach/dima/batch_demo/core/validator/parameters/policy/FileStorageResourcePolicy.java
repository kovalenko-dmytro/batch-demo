package com.gmail.apach.dima.batch_demo.core.validator.parameters.policy;

import com.gmail.apach.dima.batch_demo.core.config.message.constant.Error;
import com.gmail.apach.dima.batch_demo.core.model.batch.Parameter;
import com.gmail.apach.dima.batch_demo.core.model.batch.Parameters;
import com.gmail.apach.dima.batch_demo.core.validator.structure.policy.AbstractValidationPolicy;
import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.NonNull;

public class FileStorageResourcePolicy extends AbstractValidationPolicy<Parameters> {

    @Override
    public boolean satisfy(@NonNull Parameters input) {
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
