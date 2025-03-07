package com.gmail.apach.dima.batch_demo.application.batch.import_xml_zip_to_excel.job.step.unpack_zip;

import com.gmail.apach.dima.batch_demo.application.batch.import_xml_zip_to_excel.common.ImportXmlZipToExcelStep;
import com.gmail.apach.dima.batch_demo.application.batch.import_xml_zip_to_excel.job.step.unpack_zip.task.UnpackZipTask;
import com.gmail.apach.dima.batch_demo.application.core.job.configure.BaseBatchConfigure;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class UnpackZipStepConfigure {

    private final BaseBatchConfigure configure;
    private final UnpackZipTask unpackZipTask;

    @Bean
    @SuppressWarnings("unused")
    protected Step unpackZipStep() {
        return new StepBuilder(ImportXmlZipToExcelStep.UNPACK_ZIP_STEP.getName(), configure.getJobRepository())
            .tasklet(unpackZipTask, configure.getPlatformTransactionManager())
            .listener(configure.getLogStepExecutionListener())
            .exceptionHandler(configure.getBaseJobExceptionHandler())
            .build();
    }
}
