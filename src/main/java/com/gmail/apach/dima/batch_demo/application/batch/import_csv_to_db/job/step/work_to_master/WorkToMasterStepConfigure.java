package com.gmail.apach.dima.batch_demo.application.batch.import_csv_to_db.job.step.work_to_master;

import com.gmail.apach.dima.batch_demo.application.batch.import_csv_to_db.common.ImportCsvToDbStep;
import com.gmail.apach.dima.batch_demo.application.batch.import_csv_to_db.job.step.work_to_master.task.MasterItemWriter;
import com.gmail.apach.dima.batch_demo.application.batch.import_csv_to_db.job.step.work_to_master.task.WorkItemReader;
import com.gmail.apach.dima.batch_demo.application.batch.import_csv_to_db.job.step.work_to_master.task.WorkToMasterCompositeItemProcessor;
import com.gmail.apach.dima.batch_demo.application.batch.import_csv_to_db.model.MasterModel;
import com.gmail.apach.dima.batch_demo.application.core.job.configure.BaseBatchConfigure;
import com.gmail.apach.dima.batch_demo.infrastructure.adapter.output.db.work.entity.WorkTableEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class WorkToMasterStepConfigure {

    private final BaseBatchConfigure configure;

    private final WorkItemReader workItemReader;
    private final WorkToMasterCompositeItemProcessor compositeItemProcessor;
    private final MasterItemWriter masterItemWriter;

    @Bean
    @SuppressWarnings("unused")
    public Step workToMasterStep() {
        return new StepBuilder(ImportCsvToDbStep.WORK_TO_MASTER_STEP.getName(), configure.getJobRepository())
            .<WorkTableEntity, MasterModel>chunk(
                configure.getBatchConfigProperties().getBatchSize(),
                configure.getPlatformTransactionManager()
            )
            .reader(workItemReader)
            .processor(compositeItemProcessor)
            .writer(masterItemWriter)
            .listener(configure.getLogStepExecutionListener())
            .exceptionHandler(configure.getBaseJobExceptionHandler())
            .build();
    }
}
