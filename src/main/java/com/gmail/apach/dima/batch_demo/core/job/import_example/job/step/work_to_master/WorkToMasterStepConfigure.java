package com.gmail.apach.dima.batch_demo.core.job.import_example.job.step.work_to_master;

import com.gmail.apach.dima.batch_demo.core.base.job.config.BatchConfigProperties;
import com.gmail.apach.dima.batch_demo.core.base.job.handler.JobExceptionHandler;
import com.gmail.apach.dima.batch_demo.core.base.job.listener.BaseStepExecutionListener;
import com.gmail.apach.dima.batch_demo.core.job.import_example.common.ImportExampleStepName;
import com.gmail.apach.dima.batch_demo.core.job.import_example.job.step.work_to_master.listener.JobPromotionListener;
import com.gmail.apach.dima.batch_demo.core.job.import_example.job.step.work_to_master.task.MasterItemWriter;
import com.gmail.apach.dima.batch_demo.core.job.import_example.job.step.work_to_master.task.WorkItemReader;
import com.gmail.apach.dima.batch_demo.core.job.import_example.job.step.work_to_master.task.WorkToMasterItemProcessor;
import com.gmail.apach.dima.batch_demo.infrastructure.adapter.output.db.example.entity.MasterExampleEntity;
import com.gmail.apach.dima.batch_demo.infrastructure.adapter.output.db.example.entity.WorkExampleEntity;
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
    private final WorkItemReader workItemReader;
    private final WorkToMasterItemProcessor workToMasterItemProcessor;
    private final MasterItemWriter masterItemWriter;
    private final BaseStepExecutionListener baseStepExecutionListener;
    private final JobPromotionListener jobPromotionListener;
    private final JobExceptionHandler exceptionHandler;
    private final BatchConfigProperties batchConfigProperties;

    @Bean
    @SuppressWarnings("unused")
    public Step workToMasterStep() {
        return new StepBuilder(ImportExampleStepName.WORK_TO_MASTER_STEP.getName(), jobRepository)
            .<WorkExampleEntity, MasterExampleEntity>chunk(batchConfigProperties.getBatchSize(), transactionManager)
            .reader(workItemReader)
            .processor(workToMasterItemProcessor)
            .writer(masterItemWriter)
            .listener(baseStepExecutionListener)
            .listener(jobPromotionListener)
            .exceptionHandler(exceptionHandler)
            .build();
    }
}
