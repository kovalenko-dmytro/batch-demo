package com.gmail.apach.dima.batch_demo.core.job.import_example.job.step.upload_file.task;

import com.gmail.apach.dima.batch_demo.application.output.oss.AwsOssOutputPort;
import com.gmail.apach.dima.batch_demo.core.base.common.constant.JobExecutionContextKey;
import com.gmail.apach.dima.batch_demo.core.base.model.job.Parameter;
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
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;

@Slf4j
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@RequiredArgsConstructor
public class UploadFileTask implements Tasklet, StepExecutionListener {

    private final AwsOssOutputPort awsOssOutputPort;
    private final MessageUtil messageUtil;

    private String jobName;
    private long jobId;
    private String stepName;
    private String fileStorageResource;
    private Resource jobResource;

    @Override
    public void beforeStep(@NonNull StepExecution stepExecution) {
        final var jobExecution = stepExecution.getJobExecution();
        this.jobName = jobExecution.getJobInstance().getJobName();
        this.jobId = jobExecution.getJobInstance().getInstanceId();
        this.stepName = stepExecution.getStepName();
        this.fileStorageResource = jobExecution
            .getJobParameters()
            .getString(Parameter.FILE_STORAGE_RESOURCE.getArg());
        log.info(messageUtil.getMessage(Info.JOB_STEP_STARTED, jobName, jobId, stepName));
    }

    @NonNull
    @Override
    public RepeatStatus execute(
        @NonNull StepContribution contribution,
        @NonNull ChunkContext chunkContext
    ) {
        final var storedResource = awsOssOutputPort.get(fileStorageResource);
        this.jobResource = new InputStreamResource(new ByteArrayInputStream(storedResource.getPayload()));
        log.info(messageUtil.getMessage(Info.JOB_STEP_PROCESSED, jobName, jobId, stepName));
        return RepeatStatus.FINISHED;
    }

    @NonNull
    @Override
    public ExitStatus afterStep(@NonNull StepExecution stepExecution) {
        final var exceptions = stepExecution.getFailureExceptions();

        if (exceptions.isEmpty()) {
            stepExecution
                .getJobExecution()
                .getExecutionContext()
                .put(JobExecutionContextKey.JOB_FILE_RESOURCE, jobResource);

            log.info(messageUtil.getMessage(
                Info.JOB_STEP_COMPLETED,
                jobName, jobId, stepName, ExitStatus.COMPLETED.getExitCode()));

            return ExitStatus.COMPLETED;
        }

        log.info(messageUtil.getMessage(
            Info.JOB_STEP_COMPLETED,
            jobName, jobId, stepName, ExitStatus.FAILED.getExitCode()));

        return ExitStatus.FAILED;
    }
}
