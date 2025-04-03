package com.gmail.apach.dima.batch_demo.manager.application.job.service;

import com.gmail.apach.dima.batch_demo.common.model.RequestParameter;
import com.gmail.apach.dima.batch_demo.common.model.RequestParameters;
import com.gmail.apach.dima.batch_demo.manager.application.job.validator.JobExecutionValidator;
import com.gmail.apach.dima.batch_demo.manager.application.job.validator.JobRegistrationValidator;
import com.gmail.apach.dima.batch_demo.manager.port.input.job.ExecuteJobInputPort;
import com.gmail.apach.dima.batch_demo.manager.port.output.rest.ExecuteJobOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExecuteJobService implements ExecuteJobInputPort {

    private final JobRegistrationValidator jobRegistrationValidator;
    private final JobExecutionService jobExecutionService;
    private final JobExecutionValidator jobExecutionValidator;
    private final ExecuteJobOutputPort executeJobOutputPort;

    @Override
    public HttpStatus execute(RequestParameters parameters) {
        final var jobName = parameters.get(RequestParameter.JOB_NAME);

        jobRegistrationValidator.checkRegistration(jobName);
        jobExecutionService.getLastJobExecution(jobName)
            .ifPresent(jobExecution -> jobExecutionValidator.checkNotStarted(jobExecution.getStatus()));
        final var executions = jobExecutionService.getJobExecutionsWithParameters(jobName, parameters);
        jobExecutionValidator.checkUniqueParameters(executions, parameters);

        return executeJobOutputPort.execute(parameters);
    }
}
