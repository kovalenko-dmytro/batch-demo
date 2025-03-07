package com.gmail.apach.dima.batch_demo.application.batch.import_csv_to_db.job;

import com.gmail.apach.dima.batch_demo.application.batch.import_csv_to_db.job.listener.CleanupOnFailureJobListener;
import com.gmail.apach.dima.batch_demo.application.core.job.configure.BaseBatchConfigure;
import com.gmail.apach.dima.batch_demo.application.core.job.registry.JobRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ImportCsvToDbJobConfigure {

    private final BaseBatchConfigure configure;
    private final Step truncateWorkStep;
    private final Step uploadFileStep;
    private final Step fileToWorkStep;
    private final Step workToMasterStep;
    private final CleanupOnFailureJobListener cleanupOnFailureJobListener;

    @Bean(name = JobRegistry.IMPORT_CSV_TO_DB)
    public Job job() {
        return new JobBuilder(JobRegistry.IMPORT_CSV_TO_DB, configure.getJobRepository())
            .validator(configure.getFileResourceValidator())
            .listener(configure.getLogJobFailuresListener())
            .listener(cleanupOnFailureJobListener)
            .start(truncateWorkStep)
            .next(uploadFileStep)
            .next(fileToWorkStep)
            .next(workToMasterStep)
            .next(truncateWorkStep)
            .build();
    }
}
