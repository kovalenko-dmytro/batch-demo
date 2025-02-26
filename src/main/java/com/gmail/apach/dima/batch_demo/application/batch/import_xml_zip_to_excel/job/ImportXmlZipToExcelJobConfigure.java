package com.gmail.apach.dima.batch_demo.application.batch.import_xml_zip_to_excel.job;

import com.gmail.apach.dima.batch_demo.application.batch.import_xml_zip_to_excel.job.validator.XmlZipToExcelJobParametersValidator;
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
public class ImportXmlZipToExcelJobConfigure {

    private final JobRepository jobRepository;
    private final Step importlZipStep;
    private final Step unpackZipStep;
    private final Step createExcelStep;

    private final Step exportExcelStep;
    private final XmlZipToExcelJobParametersValidator jobParametersValidator;
    private final LogJobFailuresListener logJobFailuresListener;

    @Bean(name = JobRegistry.IMPORT_XML_ZIP_TO_EXCEL)
    public Job job() {
        return new JobBuilder(JobRegistry.IMPORT_XML_ZIP_TO_EXCEL, jobRepository)
            .validator(jobParametersValidator)
            .listener(logJobFailuresListener)
            .start(importlZipStep)
            .next(unpackZipStep)
            .next(createExcelStep)

            .next(exportExcelStep)
            .build();
    }
}
