package com.gmail.apach.dima.batch_demo.application.batch.import_excel_to_csv.job;

import com.gmail.apach.dima.batch_demo.application.batch.import_excel_to_csv.job.validator.ExcelToCsvJobParametersValidator;
import com.gmail.apach.dima.batch_demo.application.core.job.listener.LogJobFailuresListener;
import com.gmail.apach.dima.batch_demo.application.core.job.registry.JobRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ExcelToCsvJobConfigure {

    private final JobRepository jobRepository;
    private final Step importExcelStep;
    private final Step excelToCsvStep;
    private final Step exportCsvStep;
    private final ExcelToCsvJobParametersValidator jobParametersValidator;
    private final LogJobFailuresListener logJobFailuresListener;

    @Bean(name = JobRegistry.IMPORT_EXCEL_TO_CSV)
    public Job job() {
        return new JobBuilder(JobRegistry.IMPORT_EXCEL_TO_CSV, jobRepository)
            .validator(jobParametersValidator)
            .listener(logJobFailuresListener)
            .start(importExcelStep)
            .next(excelToCsvStep)
            .next(exportCsvStep)
            .build();
    }
}
