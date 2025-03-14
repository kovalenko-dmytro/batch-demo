package com.gmail.apach.dima.batch_demo.application.core.job.listener;

import com.gmail.apach.dima.batch_demo.common.constant.Info;
import com.gmail.apach.dima.batch_demo.common.util.MessageUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class LogStepExecutionListener implements StepExecutionListener {

    private final MessageUtil messageUtil;

    @Override
    public void beforeStep(@NonNull StepExecution stepExecution) {
        final var jobInstance = stepExecution.getJobExecution().getJobInstance();
        log.info(messageUtil.getMessage(
            Info.JOB_STEP_STARTED,
            jobInstance.getJobName(),
            jobInstance.getInstanceId(),
            stepExecution.getStepName()));
    }

    @NonNull
    @Override
    public ExitStatus afterStep(@NonNull StepExecution stepExecution) {
        final var jobInstance = stepExecution.getJobExecution().getJobInstance();
        final var exceptions = stepExecution.getFailureExceptions();
        final var status = exceptions.isEmpty() ? ExitStatus.COMPLETED : ExitStatus.FAILED;

        log.info(messageUtil.getMessage(
            Info.JOB_STEP_COMPLETED,
            jobInstance.getJobName(),
            jobInstance.getInstanceId(),
            stepExecution.getStepName(),
            status.getExitCode()));

        return status;
    }
}
