package com.gmail.apach.dima.batch_demo.core.job.import_example.validator;

import com.gmail.apach.dima.batch_demo.core.base.common.validator.Validator;
import com.gmail.apach.dima.batch_demo.core.base.model.job.RequestParameters;
import com.gmail.apach.dima.batch_demo.core.job.import_example.validator.policy.FileStorageResourcePolicy;
import com.gmail.apach.dima.batch_demo.infrastructure.common.message.MessageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
@RequiredArgsConstructor
public class JobParametersValidator implements Validator<RequestParameters> {

    private final MessageUtil messageUtil;

    @Override
    public void validate(RequestParameters input) throws JobParametersInvalidException {
        final var policy = new FileStorageResourcePolicy();
        try {
            Assert.state(
                policy.satisfy(input),
                messageUtil.getMessage(policy.errorCode(), policy.errorParams()));
        } catch (IllegalArgumentException e) {
            throw new JobParametersInvalidException(e.getMessage());
        }
    }
}
