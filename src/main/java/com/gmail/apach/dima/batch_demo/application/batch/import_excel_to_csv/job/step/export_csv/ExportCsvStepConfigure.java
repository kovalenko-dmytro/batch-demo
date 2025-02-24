package com.gmail.apach.dima.batch_demo.application.batch.import_excel_to_csv.job.step.export_csv;

import com.gmail.apach.dima.batch_demo.application.batch.import_excel_to_csv.common.ImportExcelToCsvStep;
import com.gmail.apach.dima.batch_demo.application.batch.import_excel_to_csv.job.step.export_csv.task.UploadCsvTask;
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
public class ExportCsvStepConfigure {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;
    private final UploadCsvTask uploadCsvTask;
    private final LogStepExecutionListener logStepExecutionListener;
    private final BaseJobExceptionHandler exceptionHandler;

    @Bean
    @SuppressWarnings("unused")
    protected Step exportCsvStep() {
        return new StepBuilder(ImportExcelToCsvStep.EXPORT_CSV_STEP.getName(), jobRepository)
            .tasklet(uploadCsvTask, transactionManager)
            .listener(logStepExecutionListener)
            .exceptionHandler(exceptionHandler)
            .build();
    }
}
