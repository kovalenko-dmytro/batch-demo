package com.gmail.apach.dima.batch_demo.application.batch.import_csv_to_db.job.step.file_to_work;

import com.gmail.apach.dima.batch_demo.application.batch.import_csv_to_db.common.ImportCsvToDbStep;
import com.gmail.apach.dima.batch_demo.application.batch.import_csv_to_db.job.step.file_to_work.task.FileItemReader;
import com.gmail.apach.dima.batch_demo.application.batch.import_csv_to_db.job.step.file_to_work.task.FileToWorkCompositeItemProcessor;
import com.gmail.apach.dima.batch_demo.application.batch.import_csv_to_db.job.step.file_to_work.task.WorkItemWriter;
import com.gmail.apach.dima.batch_demo.application.batch.import_csv_to_db.model.CsvLineModel;
import com.gmail.apach.dima.batch_demo.application.batch.import_csv_to_db.model.WorkModel;
import com.gmail.apach.dima.batch_demo.application.core.job.handler.BaseJobExceptionHandler;
import com.gmail.apach.dima.batch_demo.application.core.job.listener.LogStepExecutionListener;
import com.gmail.apach.dima.batch_demo.infrastructure.common.batch.BatchConfigProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@RequiredArgsConstructor
public class FileToWorkStepConfigure {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;
    private final BatchConfigProperties batchConfigProperties;
    private final FileItemReader fileItemReader;
    private final FileToWorkCompositeItemProcessor compositeItemProcessor;
    private final WorkItemWriter workItemWriter;
    private final BaseJobExceptionHandler exceptionHandler;
    private final LogStepExecutionListener logStepExecutionListener;

    @Bean
    @SuppressWarnings("unused")
    public Step fileToWorkStep() {
        return new StepBuilder(ImportCsvToDbStep.FILE_TO_WORK_STEP.getName(), jobRepository)
            .<CsvLineModel, WorkModel>chunk(batchConfigProperties.getBatchSize(), transactionManager)
            .reader(fileItemReader)
            .processor(compositeItemProcessor)
            .writer(workItemWriter)
            .exceptionHandler(exceptionHandler)
            .listener(logStepExecutionListener)
            .build();
    }
}
