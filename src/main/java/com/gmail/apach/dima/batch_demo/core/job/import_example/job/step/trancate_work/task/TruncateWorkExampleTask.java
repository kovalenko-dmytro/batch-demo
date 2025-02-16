package com.gmail.apach.dima.batch_demo.core.job.import_example.job.step.trancate_work.task;

import com.gmail.apach.dima.batch_demo.application.output.db.WorkExampleOutputPort;
import com.gmail.apach.dima.batch_demo.infrastructure.common.message.MessageUtil;
import com.gmail.apach.dima.batch_demo.infrastructure.common.message.code.Info;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@RequiredArgsConstructor
public class TruncateWorkExampleTask implements Tasklet, StepExecutionListener {

    private final WorkExampleOutputPort workExampleOutputPort;
    private final MessageUtil messageUtil;

    private String jobName;
    private long jobId;
    private String stepName;

    @Override
    public void beforeStep(@NonNull StepExecution stepExecution) {
        final var jobInstance = stepExecution.getJobExecution().getJobInstance();
        this.jobName = jobInstance.getJobName();
        this.jobId = jobInstance.getInstanceId();
        this.stepName = stepExecution.getStepName();
        log.info(messageUtil.getMessage(Info.JOB_STEP_STARTED, jobName, jobId, stepName));
    }

    @NonNull
    @Override
    public RepeatStatus execute(
        @NonNull StepContribution contribution,
        @NonNull ChunkContext chunkContext
    ) {
        workExampleOutputPort.truncate();
        log.info(messageUtil.getMessage(Info.JOB_STEP_PROCESSED, jobName, jobId, stepName));
        return RepeatStatus.FINISHED;
    }

    @NonNull
    @Override
    public ExitStatus afterStep(@NonNull StepExecution stepExecution) {
        final var exitStatus = ExitStatus.COMPLETED;
        log.info(messageUtil.getMessage(Info.JOB_STEP_COMPLETED, jobName, jobId, stepName, exitStatus.getExitCode()));
        return exitStatus;
    }
}
