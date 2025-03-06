package com.gmail.apach.dima.batch_demo.application.batch.import_xml_zip_to_excel.job;

import com.gmail.apach.dima.batch_demo.application.batch.import_xml_zip_to_excel.job.validator.XmlZipToExcelJobParametersValidator;
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
public class ImportXmlZipToExcelJobConfigure {

    private final BaseBatchConfigure configure;

    private final Step importlZipStep;
    private final Step unpackZipStep;
    private final Step createExcelStep;
    private final Step processTemplateStep;
    private final Step processSettingStep;
    private final Step processConfigStep;
    private final Step exportExcelStep;
    private final XmlZipToExcelJobParametersValidator jobParametersValidator;

    @Bean(name = JobRegistry.IMPORT_XML_ZIP_TO_EXCEL)
    public Job job() {
        return new JobBuilder(JobRegistry.IMPORT_XML_ZIP_TO_EXCEL, configure.getJobRepository())
            .validator(jobParametersValidator)
            .listener(configure.getLogJobFailuresListener())
            .start(importlZipStep)
            .next(unpackZipStep)
            .next(createExcelStep)
            .next(processTemplateStep)
            .next(processSettingStep)
            .next(processConfigStep)
            .next(exportExcelStep)
            .build();
    }
}
