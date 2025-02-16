package com.gmail.apach.dima.batch_demo.core.job.import_example.job;

import com.gmail.apach.dima.batch_demo.core.base.job.registry.JobRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.JobParametersValidator;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ExampleJob {

    private final JobRepository jobRepository;

    private final Step truncateWorkStep;
    private final Step uploadFileStep;
    private final Step fileToWorkStep;
    //private final Step workToMasterStep;
    JobParametersValidator exampleJobValidator;
    private final JobExecutionListener baseJobExecutionListener;

    @Bean(name = JobRegistry.IMPORT_EXAMPLE)
    public Job job() {
        return new JobBuilder(JobRegistry.IMPORT_EXAMPLE, jobRepository)
            .validator(exampleJobValidator)
            .listener(baseJobExecutionListener)
            .start(truncateWorkStep)
            .next(uploadFileStep)
            .next(fileToWorkStep)
            //.next(workToMasterStep)
            .build();
    }
}
