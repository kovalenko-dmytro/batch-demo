package com.gmail.apach.dima.batch_demo.core.job.import_example.job.step.file_to_work;

import com.gmail.apach.dima.batch_demo.core.base.job.config.BatchConfigProperties;
import com.gmail.apach.dima.batch_demo.core.base.job.handler.JobExceptionHandler;
import com.gmail.apach.dima.batch_demo.core.base.job.listener.BaseStepExecutionListener;
import com.gmail.apach.dima.batch_demo.core.job.import_example.common.ImportExampleStepName;
import com.gmail.apach.dima.batch_demo.core.job.import_example.job.step.file_to_work.task.ChunkItemWriter;
import com.gmail.apach.dima.batch_demo.core.job.import_example.job.step.file_to_work.task.FileItemReader;
import com.gmail.apach.dima.batch_demo.core.job.import_example.job.step.file_to_work.task.LineItemProcessor;
import com.gmail.apach.dima.batch_demo.core.job.import_example.model.WorkLine;
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
public class FileToWorkStepConfigure {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;
    private final FileItemReader fileItemReader;
    private final LineItemProcessor lineItemProcessor;
    private final ChunkItemWriter chunkItemWriter;
    private final BaseStepExecutionListener baseStepExecutionListener;
    private final JobExceptionHandler exceptionHandler;
    private final BatchConfigProperties batchConfigProperties;

    @Bean
    @SuppressWarnings("unused")
    public Step fileToWorkStep() {
        return new StepBuilder(ImportExampleStepName.FILE_TO_WORK_STEP.getName(), jobRepository)
            .<WorkLine, WorkExampleEntity>chunk(batchConfigProperties.getBatchSize(), transactionManager)
            .reader(fileItemReader)
            .processor(lineItemProcessor)
            .writer(chunkItemWriter)
            .listener(baseStepExecutionListener)
            .exceptionHandler(exceptionHandler)
            .build();
    }
}
