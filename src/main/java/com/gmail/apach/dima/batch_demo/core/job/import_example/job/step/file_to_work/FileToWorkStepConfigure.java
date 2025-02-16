package com.gmail.apach.dima.batch_demo.core.job.import_example.job.step.file_to_work;

import com.gmail.apach.dima.batch_demo.core.base.job.handler.JobExceptionHandler;
import com.gmail.apach.dima.batch_demo.core.job.import_example.job.step.file_to_work.task.processor.ExampleProcessor;
import com.gmail.apach.dima.batch_demo.core.job.import_example.job.step.file_to_work.task.reader.ExampleReader;
import com.gmail.apach.dima.batch_demo.core.job.import_example.job.step.file_to_work.task.writer.ExampleWriter;
import com.gmail.apach.dima.batch_demo.core.job.import_example.model.WorkLine;
import com.gmail.apach.dima.batch_demo.infrastructure.adapter.output.db.example.entity.ExampleEntity;
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
    private final ExampleReader exampleReader;
    private final ExampleProcessor exampleProcessor;
    private final ExampleWriter exampleWriter;
    private final JobExceptionHandler exceptionHandler;

    @Bean
    public Step fileToWorkStep() {
        return new StepBuilder("FILE-TO-WORK-STEP", jobRepository)
            .<WorkLine, ExampleEntity>chunk(10, transactionManager)
            .exceptionHandler(exceptionHandler)
            .reader(exampleReader)
            .processor(exampleProcessor)
            .writer(exampleWriter)
            .build();
    }
}
