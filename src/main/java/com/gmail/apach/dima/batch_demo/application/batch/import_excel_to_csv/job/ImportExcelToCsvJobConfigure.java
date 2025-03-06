package com.gmail.apach.dima.batch_demo.application.batch.import_excel_to_csv.job;

import com.gmail.apach.dima.batch_demo.application.batch.import_excel_to_csv.job.validator.ExcelToCsvJobParametersValidator;
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
public class ImportExcelToCsvJobConfigure {

    private final BaseBatchConfigure configure;

    private final Step importExcelStep;
    private final Step excelToCsvStep;
    private final Step exportCsvStep;
    private final ExcelToCsvJobParametersValidator jobParametersValidator;

    @Bean(name = JobRegistry.IMPORT_EXCEL_TO_CSV)
    public Job job() {
        return new JobBuilder(JobRegistry.IMPORT_EXCEL_TO_CSV, configure.getJobRepository())
            .validator(jobParametersValidator)
            .listener(configure.getLogJobFailuresListener())
            .start(importExcelStep)
            .next(excelToCsvStep)
            .next(exportCsvStep)
            .build();
    }
}
