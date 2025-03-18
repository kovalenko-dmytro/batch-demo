package com.gmail.apach.dima.batch_demo.application.core.job.service;

import com.gmail.apach.dima.batch_demo.common.constant.Error;
import com.gmail.apach.dima.batch_demo.common.constant.Resource;
import com.gmail.apach.dima.batch_demo.common.exception.ResourceNotFoundException;
import com.gmail.apach.dima.batch_demo.common.util.MessageUtil;
import com.gmail.apach.dima.batch_demo.port.input.job.CheckJobRegistrationInputPort;
import com.gmail.apach.dima.batch_demo.port.output.db.job.RegisteredJobExistsOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CheckJobRegistrationService implements CheckJobRegistrationInputPort {

    private final RegisteredJobExistsOutputPort registeredJobExistsOutputPort;
    private final MessageUtil messageUtil;

    @Override
    public void check(@NonNull String jobName) {
        final var exist = registeredJobExistsOutputPort.exist(jobName);
        if (!exist) {
            throw new ResourceNotFoundException(
                messageUtil.getMessage(
                    Error.JOB_NOT_REGISTERED,
                    Resource.Attribute.NAME.getName(),
                    jobName));
        }
    }
}
