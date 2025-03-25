package com.gmail.apach.dima.batch_demo.application.core.job.validator;

import com.gmail.apach.dima.batch_demo.common.constant.Error;
import com.gmail.apach.dima.batch_demo.common.constant.Resource;
import com.gmail.apach.dima.batch_demo.common.exception.ResourceNotFoundException;
import com.gmail.apach.dima.batch_demo.common.util.MessageUtil;
import com.gmail.apach.dima.batch_demo.port.output.db.job.RegisteredJobExistsOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
@RequiredArgsConstructor
public class JobRegistrationValidator {

    private final RegisteredJobExistsOutputPort registeredJobExistsOutputPort;
    private final MessageUtil messageUtil;

    public void checkRegistration(@NonNull String jobName) {
        try {
            Assert.state(
                registeredJobExistsOutputPort.exist(jobName),
                messageUtil.getMessage(Error.JOB_NOT_REGISTERED, Resource.Attribute.NAME.getName(), jobName));
        } catch (IllegalStateException e) {
            throw new ResourceNotFoundException(e.getMessage());
        }
    }
}
