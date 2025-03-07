package com.gmail.apach.dima.batch_demo.application.batch.import_csv_to_db.job.step.upload_file;

import com.gmail.apach.dima.batch_demo.application.batch.import_csv_to_db.common.ImportCsvToDbStep;
import com.gmail.apach.dima.batch_demo.application.batch.import_csv_to_db.job.step.upload_file.task.UploadFileTask;
import com.gmail.apach.dima.batch_demo.application.core.job.configure.BaseBatchConfigure;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class UploadFileStepConfigure {

    private final BaseBatchConfigure configure;
    private final UploadFileTask uploadFileTask;

    @Bean
    @SuppressWarnings("unused")
    protected Step uploadFileStep() {
        return new StepBuilder(ImportCsvToDbStep.UPLOAD_FILE_STEP.getName(), configure.getJobRepository())
            .tasklet(uploadFileTask, configure.getPlatformTransactionManager())
            .listener(configure.getLogStepExecutionListener())
            .exceptionHandler(configure.getBaseJobExceptionHandler())
            .build();
    }
}
