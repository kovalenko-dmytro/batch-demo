package com.gmail.apach.dima.batch_demo.infrastructure.adapter.input.cli.validation;

import com.gmail.apach.dima.batch_demo.core.base.job.model.RequestParameter;
import com.gmail.apach.dima.batch_demo.core.base.job.model.RequestParameters;
import com.gmail.apach.dima.batch_demo.infrastructure.common.message.MessageUtil;
import com.gmail.apach.dima.batch_demo.infrastructure.common.message.code.Error;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.batch.item.validator.Validator;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Slf4j
@Component
@RequiredArgsConstructor
public class CliRequestValidator implements Validator<RequestParameters> {

    private final MessageUtil messageUtil;

    public void validate(RequestParameters value) {
        try {
            Assert.isTrue(
                StringUtils.isNoneBlank(value.get(RequestParameter.JOB_NAME)),
                messageUtil.getMessage(Error.BATCH_NAME_NOT_DEFINED, RequestParameter.JOB_NAME.getArg()));
        } catch (IllegalArgumentException e) {
            log.error(e.getMessage());
        }
    }
}
