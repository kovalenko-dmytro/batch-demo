package com.gmail.apach.dima.batch_demo.application.core.job.executor;

import com.gmail.apach.dima.batch_demo.application.core.job.model.RequestParameter;
import com.gmail.apach.dima.batch_demo.application.core.job.model.RequestParameters;
import com.gmail.apach.dima.batch_demo.infrastructure.common.message.MessageUtil;
import com.gmail.apach.dima.batch_demo.infrastructure.common.message.code.Error;
import com.gmail.apach.dima.batch_demo.infrastructure.common.message.code.Info;
import com.gmail.apach.dima.batch_demo.port.input.job.JobExecutionInputPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobInterruptedException;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class BatchExecutor implements JobExecutionInputPort {

    private final JobExplorer jobExplorer;
    private final ApplicationContext context;
    private final JobLauncher jobLauncher;
    private final MessageUtil messageUtil;

    @Async
    @Override
    public void execute(RequestParameters parameters) {
        final var jobName = parameters.get(RequestParameter.JOB_NAME);
        try {
            checkAlreadyRunning(jobName);

            final var job = context.getBean(jobName, Job.class);
            log.info(messageUtil.getMessage(Info.JOB_INITIALIZED, job.getName()));
            log.info(messageUtil.getMessage(Info.JOB_EXEC_MARK, parameters.get(RequestParameter.JOB_EXEC_MARK)));

            final var execution = jobLauncher.run(job, parameters.toJobParameters());
            log.info(messageUtil.getMessage(Info.JOB_FINISHED, job.getName(), execution.getStatus().name()));
        } catch (Exception e) {
            log.error(messageUtil.getMessage(Error.JOB_FAILED, jobName, e.getMessage()));
        }
    }

    private void checkAlreadyRunning(String jobName) throws JobInterruptedException {
        final var lastJobInstance = jobExplorer.getLastJobInstance(jobName);
        final var lastJobExecution = jobExplorer.getLastJobExecution(lastJobInstance);
        final var batchStatus = lastJobExecution.getStatus();
        if (BatchStatus.STARTED.equals(batchStatus) || BatchStatus.STARTING.equals(batchStatus)) {
            final var message = """
                Job: <%s> previous execution still have status: %s
                """.formatted(jobName, batchStatus.name());
            throw new JobInterruptedException(
                messageUtil.getMessage(Error.JOB_INTERRUPTED, message), BatchStatus.FAILED);
        }
    }
}
