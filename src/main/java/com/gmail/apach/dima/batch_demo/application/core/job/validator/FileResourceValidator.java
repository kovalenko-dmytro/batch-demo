package com.gmail.apach.dima.batch_demo.application.core.job.validator;

import com.gmail.apach.dima.batch_demo.application.core.job.model.RequestParameter;
import com.gmail.apach.dima.batch_demo.common.constant.Delimiter;
import com.gmail.apach.dima.batch_demo.common.util.MessageUtil;
import com.gmail.apach.dima.batch_demo.common.validator.policy.implementation.FileStorageResourcePolicy;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.JobParametersValidator;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class FileResourceValidator implements JobParametersValidator {

    private final MessageUtil messageUtil;

    @Override
    public void validate(@NonNull JobParameters parameters) throws JobParametersInvalidException {
        final var resource = Optional
            .ofNullable(parameters.getParameters().get(RequestParameter.FILE_STORAGE_RESOURCE.getName()))
            .map(parameter -> (String) parameter.getValue())
            .orElse(Delimiter.EMPTY);
        final var policy = new FileStorageResourcePolicy();
        try {
            Assert.state(
                policy.satisfy(resource),
                messageUtil.getMessage(policy.errorCode(), policy.errorParams()));
        } catch (IllegalStateException e) {
            throw new JobParametersInvalidException(e.getMessage());
        }
    }
}
