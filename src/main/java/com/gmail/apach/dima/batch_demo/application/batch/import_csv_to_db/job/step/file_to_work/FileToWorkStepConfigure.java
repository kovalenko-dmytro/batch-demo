package com.gmail.apach.dima.batch_demo.application.batch.import_csv_to_db.job.step.file_to_work;

import com.gmail.apach.dima.batch_demo.application.batch.import_csv_to_db.common.ImportCsvToDbStep;
import com.gmail.apach.dima.batch_demo.application.batch.import_csv_to_db.job.step.file_to_work.task.FileItemReader;
import com.gmail.apach.dima.batch_demo.application.batch.import_csv_to_db.job.step.file_to_work.task.FileToWorkCompositeItemProcessor;
import com.gmail.apach.dima.batch_demo.application.batch.import_csv_to_db.job.step.file_to_work.task.WorkItemWriter;
import com.gmail.apach.dima.batch_demo.application.batch.import_csv_to_db.model.CsvLineModel;
import com.gmail.apach.dima.batch_demo.application.batch.import_csv_to_db.model.WorkModel;
import com.gmail.apach.dima.batch_demo.application.core.job.configure.BaseBatchConfigure;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class FileToWorkStepConfigure {

    private final BaseBatchConfigure configure;

    private final FileItemReader fileItemReader;
    private final FileToWorkCompositeItemProcessor compositeItemProcessor;
    private final WorkItemWriter workItemWriter;

    @Bean
    @SuppressWarnings("unused")
    public Step fileToWorkStep() {
        return new StepBuilder(ImportCsvToDbStep.FILE_TO_WORK_STEP.getName(), configure.getJobRepository())
            .<CsvLineModel, WorkModel>chunk(
                configure.getBatchConfigProperties().getBatchSize(),
                configure.getPlatformTransactionManager()
            )
            .reader(fileItemReader)
            .processor(compositeItemProcessor)
            .writer(workItemWriter)
            .exceptionHandler(configure.getBaseJobExceptionHandler())
            .listener(configure.getLogStepExecutionListener())
            .build();
    }
}
