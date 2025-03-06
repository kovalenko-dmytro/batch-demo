package com.gmail.apach.dima.batch_demo.application.batch.import_xml_zip_to_excel.job.step.export_excel;

import com.gmail.apach.dima.batch_demo.application.batch.import_xml_zip_to_excel.common.ImportXmlZipToExcelStep;
import com.gmail.apach.dima.batch_demo.application.batch.import_xml_zip_to_excel.job.step.export_excel.task.UploadExcelTask;
import com.gmail.apach.dima.batch_demo.application.core.job.configure.BaseBatchConfigure;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ExportExcelStepConfigure {

    private final BaseBatchConfigure configure;
    private final UploadExcelTask uploadExcelTask;

    @Bean
    @SuppressWarnings("unused")
    protected Step exportExcelStep() {
        return new StepBuilder(ImportXmlZipToExcelStep.EXPORT_EXCEL.getName(), configure.getJobRepository())
            .tasklet(uploadExcelTask, configure.getPlatformTransactionManager())
            .listener(configure.getLogStepExecutionListener())
            .exceptionHandler(configure.getBaseJobExceptionHandler())
            .build();
    }
}
