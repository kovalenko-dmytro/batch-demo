package com.gmail.apach.dima.batch_demo.core.validator.parameters;

import com.gmail.apach.dima.batch_demo.core.config.batch.JobRegistry;
import com.gmail.apach.dima.batch_demo.core.config.message.constant.Error;
import com.gmail.apach.dima.batch_demo.core.config.message.constant.Info;
import com.gmail.apach.dima.batch_demo.core.exception.ValidationException;
import com.gmail.apach.dima.batch_demo.core.model.batch.Parameter;
import com.gmail.apach.dima.batch_demo.core.model.batch.Parameters;
import com.gmail.apach.dima.batch_demo.core.service.message.MessageService;
import com.gmail.apach.dima.batch_demo.core.validator.parameters.policy.FileStorageResourcePolicy;
import com.gmail.apach.dima.batch_demo.core.validator.structure.Validator;
import com.gmail.apach.dima.batch_demo.core.validator.structure.policy.ValidationPolicy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.Map;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class BatchParametersValidator implements Validator<Parameters> {

    private final MessageService messageService;

    private final Map<String, ValidationPolicy<Parameters>> policies =
        Map.of(JobRegistry.EXAMPLE, new FileStorageResourcePolicy());

    @Override
    public void validate(Parameters input) throws ValidationException {
        try {
            Assert.state(
                StringUtils.isNoneBlank(input.get(Parameter.BATCH_NAME)),
                messageService.message(Error.BATCH_NAME_NOT_DEFINED, Parameter.BATCH_NAME.getArg()));

            Optional
                .ofNullable(policies.get(input.get(Parameter.BATCH_NAME)))
                .ifPresent(policy ->
                    Assert.state(
                        policy.satisfy(input),
                        messageService.message(policy.errorCode(), policy.errorParams())));

            log.info(messageService.message(Info.BATCH_PARAMETERS_VALIDATED, input.get(Parameter.BATCH_NAME)));
        } catch (IllegalArgumentException e) {
            throw new ValidationException(e.getMessage());
        }
    }
}
