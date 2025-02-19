package com.gmail.apach.dima.batch_demo.core.job.import_csv_to_db.job.step.upload_file;

import com.gmail.apach.dima.batch_demo.core.base.job.handler.BaseJobExceptionHandler;
import com.gmail.apach.dima.batch_demo.core.base.job.listener.LogStepExecutionListener;
import com.gmail.apach.dima.batch_demo.core.job.import_csv_to_db.common.ImportCsvToDbStep;
import com.gmail.apach.dima.batch_demo.core.job.import_csv_to_db.job.step.upload_file.task.UploadFileTask;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@RequiredArgsConstructor
public class UploadFileStepConfigure {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;
    private final UploadFileTask uploadFileTask;
    private final LogStepExecutionListener logStepExecutionListener;
    private final BaseJobExceptionHandler exceptionHandler;

    @Bean
    @SuppressWarnings("unused")
    protected Step uploadFileStep() {
        return new StepBuilder(ImportCsvToDbStep.UPLOAD_FILE_STEP.getName(), jobRepository)
            .tasklet(uploadFileTask, transactionManager)
            .listener(logStepExecutionListener)
            .exceptionHandler(exceptionHandler)
            .build();
    }
}
