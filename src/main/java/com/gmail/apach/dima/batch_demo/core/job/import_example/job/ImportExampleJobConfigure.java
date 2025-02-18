package com.gmail.apach.dima.batch_demo.core.job.import_example.job;

import com.gmail.apach.dima.batch_demo.core.base.job.registry.JobRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.*;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ImportExampleJobConfigure {

    private final JobRepository jobRepository;

    private final Step truncateWorkStep;
    private final Step uploadFileStep;
    private final Step fileToWorkStep;
    private final Step workToMasterStep;
    private final Step rollbackMasterStep;
    private final JobParametersValidator exampleJobValidator;
    private final JobExecutionListener baseJobExecutionListener;

    @Bean(name = JobRegistry.IMPORT_EXAMPLE)
    public Job job() {
        /*return new JobBuilder(JobRegistry.IMPORT_EXAMPLE, jobRepository)
            .validator(exampleJobValidator)
            .listener(baseJobExecutionListener)
            .start(truncateWorkStep)
            .next(uploadFileStep)
            .next(fileToWorkStep)
            .next(workToMasterStep)
            .next(truncateWorkStep)
            .build();*/

        return new JobBuilder(JobRegistry.IMPORT_EXAMPLE, jobRepository)
            .validator(exampleJobValidator)
            .listener(baseJobExecutionListener)
            .start(truncateWorkStep)
                .on(ExitStatus.FAILED.getExitCode())
                .end()
            .from(truncateWorkStep)
                .on(ExitStatus.COMPLETED.getExitCode())
                .to(uploadFileStep)
            .from(uploadFileStep)
                .on(ExitStatus.FAILED.getExitCode())
                .end()
            .from(uploadFileStep)
                .on(ExitStatus.COMPLETED.getExitCode())
                .to(fileToWorkStep)
            .from(fileToWorkStep)
                .on(ExitStatus.FAILED.getExitCode())
                .to(truncateWorkStep)
                    .on(ExitStatus.COMPLETED.getExitCode())
                    .end()
            .from(fileToWorkStep)
                .on(ExitStatus.COMPLETED.getExitCode())
                .to(workToMasterStep)
            .from(workToMasterStep)
                .on(ExitStatus.FAILED.getExitCode())
                .to(rollbackMasterStep)
                    .on(ExitStatus.COMPLETED.getExitCode())
                .end()
            .from(workToMasterStep)
                .on(ExitStatus.COMPLETED.getExitCode())
                .to(truncateWorkStep)
                .end()
            .build();
    }
}
