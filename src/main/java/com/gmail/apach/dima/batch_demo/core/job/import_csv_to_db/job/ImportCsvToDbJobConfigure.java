package com.gmail.apach.dima.batch_demo.core.job.import_csv_to_db.job;

import com.gmail.apach.dima.batch_demo.core.base.job.listener.LogJobFailuresListener;
import com.gmail.apach.dima.batch_demo.core.base.job.registry.JobRegistry;
import com.gmail.apach.dima.batch_demo.core.job.import_csv_to_db.job.listener.CleanupOnFailureJobListener;
import com.gmail.apach.dima.batch_demo.core.job.import_csv_to_db.job.validator.ImportCsvToDbJobParametersValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ImportCsvToDbJobConfigure {

    private final JobRepository jobRepository;

    private final Step truncateWorkStep;
    private final Step uploadFileStep;
    private final Step fileToWorkStep;
    private final Step workToMasterStep;
    private final ImportCsvToDbJobParametersValidator jobParametersValidator;
    private final LogJobFailuresListener logJobFailuresListener;
    private final CleanupOnFailureJobListener cleanupOnFailureJobListener;

    @Bean(name = JobRegistry.IMPORT_CSV_TO_DB)
    public Job job() {
        return new JobBuilder(JobRegistry.IMPORT_CSV_TO_DB, jobRepository)
            .validator(jobParametersValidator)
            .listener(logJobFailuresListener)
            .listener(cleanupOnFailureJobListener)
            .start(truncateWorkStep)
            .next(uploadFileStep)
            .next(fileToWorkStep)
            .next(workToMasterStep)
            .next(truncateWorkStep)
            .build();
    }
}
