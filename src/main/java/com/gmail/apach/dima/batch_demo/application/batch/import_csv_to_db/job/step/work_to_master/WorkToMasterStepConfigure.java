package com.gmail.apach.dima.batch_demo.application.batch.import_csv_to_db.job.step.work_to_master;

import com.gmail.apach.dima.batch_demo.application.batch.import_csv_to_db.common.ImportCsvToDbStep;
import com.gmail.apach.dima.batch_demo.application.batch.import_csv_to_db.job.step.work_to_master.task.MasterItemWriter;
import com.gmail.apach.dima.batch_demo.application.batch.import_csv_to_db.job.step.work_to_master.task.WorkItemReader;
import com.gmail.apach.dima.batch_demo.application.batch.import_csv_to_db.job.step.work_to_master.task.WorkToMasterItemProcessor;
import com.gmail.apach.dima.batch_demo.application.batch.import_csv_to_db.model.MasterModel;
import com.gmail.apach.dima.batch_demo.application.core.job.handler.BaseJobExceptionHandler;
import com.gmail.apach.dima.batch_demo.application.core.job.listener.LogStepExecutionListener;
import com.gmail.apach.dima.batch_demo.infrastructure.adapter.output.db.work.entity.WorkTableEntity;
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
public class WorkToMasterStepConfigure {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;
    private final BatchConfigProperties batchConfigProperties;
    private final WorkItemReader workItemReader;
    private final WorkToMasterItemProcessor workToMasterItemProcessor;
    private final MasterItemWriter masterItemWriter;
    private final LogStepExecutionListener logStepExecutionListener;
    private final BaseJobExceptionHandler exceptionHandler;

    @Bean
    @SuppressWarnings("unused")
    public Step workToMasterStep() {
        return new StepBuilder(ImportCsvToDbStep.WORK_TO_MASTER_STEP.getName(), jobRepository)
            .<WorkTableEntity, MasterModel>chunk(batchConfigProperties.getBatchSize(), transactionManager)
            .reader(workItemReader)
            .processor(workToMasterItemProcessor)
            .writer(masterItemWriter)
            .listener(logStepExecutionListener)
            .exceptionHandler(exceptionHandler)
            .build();
    }
}
