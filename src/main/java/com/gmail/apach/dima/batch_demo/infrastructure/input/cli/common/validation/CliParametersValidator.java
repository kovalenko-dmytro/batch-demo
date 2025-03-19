package com.gmail.apach.dima.batch_demo.infrastructure.input.cli.common.validation;

import com.gmail.apach.dima.batch_demo.application.core.job.model.RequestParameter;
import com.gmail.apach.dima.batch_demo.application.core.job.model.RequestParameters;
import com.gmail.apach.dima.batch_demo.common.constant.Error;
import com.gmail.apach.dima.batch_demo.common.util.MessageUtil;
import com.gmail.apach.dima.batch_demo.common.validator.Validator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Slf4j
@Component
@RequiredArgsConstructor
public class CliParametersValidator implements Validator<RequestParameters> {

    private final MessageUtil messageUtil;

    @Override
    public void validate(RequestParameters target) {
        try {
            Assert.state(
                StringUtils.isNoneBlank(target.get(RequestParameter.JOB_NAME)),
                messageUtil.getMessage(Error.JOB_NAME_NOT_DEFINED, RequestParameter.JOB_NAME.getArg()));
        } catch (IllegalStateException e) {
            log.error(e.getMessage());
            System.exit(0);
        }
    }
}
