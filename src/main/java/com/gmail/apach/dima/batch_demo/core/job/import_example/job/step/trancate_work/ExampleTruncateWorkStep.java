package com.gmail.apach.dima.batch_demo.core.job.import_example.job.step.trancate_work;

import com.gmail.apach.dima.batch_demo.core.base.job.handler.JobExceptionHandler;
import com.gmail.apach.dima.batch_demo.core.job.import_example.job.step.trancate_work.task.TruncateWorkExampleTask;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@RequiredArgsConstructor
public class ExampleTruncateWorkStep {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;
    private final TruncateWorkExampleTask truncateWorkExampleTask;
    private final JobExceptionHandler exceptionHandler;

    @Bean
    protected Step truncateWorkStep() {
        return new StepBuilder("TRUNCATE-WORK-STEP", jobRepository)
            .tasklet(truncateWorkExampleTask, transactionManager)
            .exceptionHandler(exceptionHandler)
            .build();
    }
}
