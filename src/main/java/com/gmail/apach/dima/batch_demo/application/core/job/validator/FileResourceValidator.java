package com.gmail.apach.dima.batch_demo.application.core.job.validator;

import com.gmail.apach.dima.batch_demo.application.core.common.validator.policy.implementation.FileStorageResourcePolicy;
import com.gmail.apach.dima.batch_demo.application.core.job.model.RequestParameter;
import com.gmail.apach.dima.batch_demo.infrastructure.common.message.MessageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.JobParametersValidator;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
@RequiredArgsConstructor
public class FileResourceValidator implements JobParametersValidator {

    private final MessageUtil messageUtil;

    @Override
    public void validate(@NonNull JobParameters parameters) throws JobParametersInvalidException {
        final var resource = (String) parameters
            .getParameter(RequestParameter.FILE_STORAGE_RESOURCE.getArg())
            .getValue();
        final var policy = new FileStorageResourcePolicy();
        try {
            Assert.state(
                policy.satisfy(resource),
                messageUtil.getMessage(policy.errorCode(), policy.errorParams()));
        } catch (IllegalArgumentException e) {
            throw new JobParametersInvalidException(e.getMessage());
        }
    }
}
