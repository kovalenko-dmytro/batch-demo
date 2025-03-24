package com.gmail.apach.dima.batch_demo.application.batch.import_csv_to_db.job.step.upload_file.task;

import com.gmail.apach.dima.batch_demo.application.core.file.model.StoredResource;
import com.gmail.apach.dima.batch_demo.application.core.job.constant.JobExecutionContextKey;
import com.gmail.apach.dima.batch_demo.application.core.job.model.RequestParameter;
import com.gmail.apach.dima.batch_demo.port.output.oss.OssOutputPort;
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

    private final OssOutputPort ossOutputPort;

    private String fileStorageResource;
    private StoredResource storedResource;

    @Override
    public void beforeStep(@NonNull StepExecution stepExecution) {
        this.fileStorageResource = stepExecution
            .getJobExecution()
            .getJobParameters()
            .getString(RequestParameter.FILE_STORAGE_RESOURCE.getName());
    }

    @NonNull
    @Override
    public RepeatStatus execute(
        @NonNull StepContribution contribution,
        @NonNull ChunkContext chunkContext
    ) {
        this.storedResource = ossOutputPort.get(fileStorageResource);
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
                .put(JobExecutionContextKey.STORED_RESOURCE, storedResource);
            return ExitStatus.COMPLETED;
        }
        return ExitStatus.FAILED;
    }
}
