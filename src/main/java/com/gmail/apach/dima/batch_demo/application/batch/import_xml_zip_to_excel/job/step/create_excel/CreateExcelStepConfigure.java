package com.gmail.apach.dima.batch_demo.application.batch.import_xml_zip_to_excel.job.step.create_excel;

import com.gmail.apach.dima.batch_demo.application.batch.import_xml_zip_to_excel.common.ImportXmlZipToExcelStep;
import com.gmail.apach.dima.batch_demo.application.batch.import_xml_zip_to_excel.job.step.create_excel.task.CreateExcelTask;
import com.gmail.apach.dima.batch_demo.application.core.job.configure.BaseBatchConfigure;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class CreateExcelStepConfigure {

    private final BaseBatchConfigure configure;
    private final CreateExcelTask createExcelTask;

    @Bean
    @SuppressWarnings("unused")
    protected Step createExcelStep() {
        return new StepBuilder(ImportXmlZipToExcelStep.CREATE_EXCEL.getName(), configure.getJobRepository())
            .tasklet(createExcelTask, configure.getPlatformTransactionManager())
            .listener(configure.getLogStepExecutionListener())
            .exceptionHandler(configure.getBaseJobExceptionHandler())
            .build();
    }
}
