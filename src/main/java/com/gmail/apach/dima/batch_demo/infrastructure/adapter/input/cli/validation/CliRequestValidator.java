package com.gmail.apach.dima.batch_demo.infrastructure.adapter.input.cli.validation;

import com.gmail.apach.dima.batch_demo.core.base.model.job.RequestParameter;
import com.gmail.apach.dima.batch_demo.core.base.model.job.RequestParameters;
import com.gmail.apach.dima.batch_demo.infrastructure.common.message.MessageUtil;
import com.gmail.apach.dima.batch_demo.infrastructure.common.message.code.Error;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
@RequiredArgsConstructor
public class CliRequestValidator {

    private final MessageUtil messageUtil;

    public void validate(RequestParameters input) {
        Assert.isTrue(
            StringUtils.isNoneBlank(input.get(RequestParameter.BATCH_NAME)),
            messageUtil.getMessage(Error.BATCH_NAME_NOT_DEFINED, RequestParameter.BATCH_NAME.getArg()));
    }
}
