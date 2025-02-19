package com.gmail.apach.dima.batch_demo.core.job.import_example.job.step.upload_file.task;

import com.gmail.apach.dima.batch_demo.application.output.oss.AwsOssOutputPort;
import com.gmail.apach.dima.batch_demo.core.base.job.constant.JobExecutionContextKey;
import com.gmail.apach.dima.batch_demo.core.base.model.job.Parameter;
import com.gmail.apach.dima.batch_demo.core.base.model.oss.StoredResource;
import lombok.RequiredArgsConstructor;
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

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@RequiredArgsConstructor
public class UploadFileTask implements Tasklet, StepExecutionListener {

    private final AwsOssOutputPort awsOssOutputPort;

    private String fileStorageResource;
    private StoredResource storedResource;

    @Override
    public void beforeStep(@NonNull StepExecution stepExecution) {
        final var jobExecution = stepExecution.getJobExecution();
        this.fileStorageResource = jobExecution
            .getJobParameters().getString(Parameter.FILE_STORAGE_RESOURCE.getArg());
    }

    @NonNull
    @Override
    public RepeatStatus execute(
        @NonNull StepContribution contribution,
        @NonNull ChunkContext chunkContext
    ) {
        this.storedResource = awsOssOutputPort.get(fileStorageResource);
        return RepeatStatus.FINISHED;
    }

    @NonNull
    @Override
    public ExitStatus afterStep(@NonNull StepExecution stepExecution) {
        final var exceptions = stepExecution.getFailureExceptions();
        if (exceptions.isEmpty()) {
            stepExecution.getJobExecution().getExecutionContext()
                .put(JobExecutionContextKey.STORED_RESOURCE, storedResource);
            return ExitStatus.COMPLETED;
        }
        return ExitStatus.FAILED;
    }
}
