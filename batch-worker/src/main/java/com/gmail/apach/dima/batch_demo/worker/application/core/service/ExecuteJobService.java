package com.gmail.apach.dima.batch_demo.worker.application.core.service;

import com.gmail.apach.dima.batch_demo.common.constant.Error;
import com.gmail.apach.dima.batch_demo.common.constant.Info;
import com.gmail.apach.dima.batch_demo.common.model.RequestParameter;
import com.gmail.apach.dima.batch_demo.common.model.RequestParameters;
import com.gmail.apach.dima.batch_demo.common.util.MessageUtil;
import com.gmail.apach.dima.batch_demo.worker.port.input.ExecuteJobInputPort;
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

    private final ApplicationContext context;
    private final JobLauncher jobLauncher;
    private final MessageUtil messageUtil;

    @Override
    public void execute(RequestParameters parameters) {
        final var jobName = parameters.get(RequestParameter.JOB_NAME);
        final var marker = parameters.get(RequestParameter.JOB_EXECUTION_MARKER);
        try {
            final var job = context.getBean(jobName, Job.class);
            log.info(messageUtil.getMessage(Info.JOB_INITIALIZED, jobName, marker));

            final var execution = jobLauncher.run(job, parameters.toJobParameters());
            log.info(messageUtil.getMessage(Info.JOB_FINISHED, jobName, marker, execution.getStatus()));
        } catch (Exception e) {
            log.error(messageUtil.getMessage(Error.JOB_FAILED, jobName, marker, e.getMessage()));
        }
    }
}
