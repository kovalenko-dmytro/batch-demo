package com.gmail.apach.dima.batch_demo.core.job.import_example.job.step.work_to_master;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@RequiredArgsConstructor
public class ExampleWorkToMasterStep {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;

    @Bean
    public Step workToMasterStep() {
        /*return new StepBuilder("STEP", jobRepository)
            .<ExampleLine, ExampleEntity>chunk(10, transactionManager)

            .build();*/
        return null;
    }
}
