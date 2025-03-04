package com.gmail.apach.dima.batch_demo.application.batch.import_excel_to_csv.job.validator;

import com.gmail.apach.dima.batch_demo.application.core.common.validator.policy.implementation.FileStorageResourcePolicy;
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
public class ExcelToCsvJobParametersValidator implements JobParametersValidator {

    private final MessageUtil messageUtil;

    @Override
    public void validate(@NonNull JobParameters parameters) throws JobParametersInvalidException {
        try {
            final var policy = new FileStorageResourcePolicy();
            Assert.state(
                policy.satisfy(parameters),
                messageUtil.getMessage(policy.errorCode(), policy.errorParams()));
        } catch (IllegalArgumentException e) {
            throw new JobParametersInvalidException(e.getMessage());
        }
    }
}
