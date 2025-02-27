package com.gmail.apach.dima.batch_demo.application.batch.import_xml_zip_to_excel.job.step.unpack_zip;

import com.gmail.apach.dima.batch_demo.application.batch.import_xml_zip_to_excel.common.ImportXmlZipToExcelStep;
import com.gmail.apach.dima.batch_demo.application.batch.import_xml_zip_to_excel.job.step.unpack_zip.task.UnpackZipTask;
import com.gmail.apach.dima.batch_demo.application.core.job.handler.BaseJobExceptionHandler;
import com.gmail.apach.dima.batch_demo.application.core.job.listener.LogStepExecutionListener;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@RequiredArgsConstructor
public class UnpackZipStepConfigure {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;
    private final UnpackZipTask unpackZipTask;
    private final LogStepExecutionListener logStepExecutionListener;
    private final BaseJobExceptionHandler exceptionHandler;

    @Bean
    @SuppressWarnings("unused")
    protected Step unpackZipStep() {
        return new StepBuilder(ImportXmlZipToExcelStep.UNPACK_ZIP_STEP.getName(), jobRepository)
            .tasklet(unpackZipTask, transactionManager)
            .listener(logStepExecutionListener)
            .exceptionHandler(exceptionHandler)
            .build();
    }
}
