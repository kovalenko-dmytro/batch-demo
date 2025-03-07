package com.gmail.apach.dima.batch_demo.application.batch.import_xml_zip_to_excel.job.step.import_zip;

import com.gmail.apach.dima.batch_demo.application.batch.import_xml_zip_to_excel.common.ImportXmlZipToExcelStep;
import com.gmail.apach.dima.batch_demo.application.batch.import_xml_zip_to_excel.job.step.import_zip.task.DownloadZipTask;
import com.gmail.apach.dima.batch_demo.application.core.job.configure.BaseBatchConfigure;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ImportZipStepConfigure {

    private final BaseBatchConfigure configure;
    private final DownloadZipTask downloadZipTask;

    @Bean
    @SuppressWarnings("unused")
    protected Step importlZipStep() {
        return new StepBuilder(ImportXmlZipToExcelStep.IMPORT_ZIP_STEP.getName(), configure.getJobRepository())
            .tasklet(downloadZipTask, configure.getPlatformTransactionManager())
            .listener(configure.getLogStepExecutionListener())
            .exceptionHandler(configure.getBaseJobExceptionHandler())
            .build();
    }
}
