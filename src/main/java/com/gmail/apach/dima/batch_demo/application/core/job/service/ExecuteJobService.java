package com.gmail.apach.dima.batch_demo.application.core.job.service;

import com.gmail.apach.dima.batch_demo.application.core.job.model.RequestParameter;
import com.gmail.apach.dima.batch_demo.application.core.job.model.RequestParameters;
import com.gmail.apach.dima.batch_demo.application.core.job.validator.JobStatusValidator;
import com.gmail.apach.dima.batch_demo.common.constant.Error;
import com.gmail.apach.dima.batch_demo.common.constant.Info;
import com.gmail.apach.dima.batch_demo.common.util.MessageUtil;
import com.gmail.apach.dima.batch_demo.port.input.job.ExecuteJobInputPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ExecuteJobService implements ExecuteJobInputPort {

    private final JobStatusValidator jobStatusValidator;
    private final ApplicationContext context;
    private final JobLauncher jobLauncher;
    private final MessageUtil messageUtil;

    @Override
    public void execute(RequestParameters parameters) {
        final var jobName = parameters.get(RequestParameter.JOB_NAME);
        final var jobExecutionMark = parameters.get(RequestParameter.JOB_EXEC_MARK);

        try {
            jobStatusValidator.validate(jobName);

            final var job = context.getBean(jobName, Job.class);
            log.info(messageUtil.getMessage(Info.JOB_INITIALIZED, jobName, jobExecutionMark));

            final var execution = jobLauncher.run(job, parameters.toJobParameters());
            log.info(messageUtil.getMessage(Info.JOB_FINISHED, jobName, jobExecutionMark, execution.getStatus()));
        } catch (Exception e) {
            log.error(messageUtil.getMessage(Error.JOB_FAILED, jobName, jobExecutionMark, e.getMessage()));
        }
    }
}
