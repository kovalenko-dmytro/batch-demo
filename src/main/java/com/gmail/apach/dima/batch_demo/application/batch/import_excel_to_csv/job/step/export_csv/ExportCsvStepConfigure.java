package com.gmail.apach.dima.batch_demo.application.batch.import_excel_to_csv.job.step.export_csv;

import com.gmail.apach.dima.batch_demo.application.batch.import_excel_to_csv.common.ImportExcelToCsvStep;
import com.gmail.apach.dima.batch_demo.application.batch.import_excel_to_csv.job.step.export_csv.task.UploadCsvTask;
import com.gmail.apach.dima.batch_demo.application.core.job.configure.BaseBatchConfigure;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ExportCsvStepConfigure {

    private final BaseBatchConfigure configure;
    private final UploadCsvTask uploadCsvTask;

    @Bean
    @SuppressWarnings("unused")
    protected Step exportCsvStep() {
        return new StepBuilder(ImportExcelToCsvStep.EXPORT_CSV_STEP.getName(), configure.getJobRepository())
            .tasklet(uploadCsvTask, configure.getPlatformTransactionManager())
            .listener(configure.getLogStepExecutionListener())
            .exceptionHandler(configure.getBaseJobExceptionHandler())
            .build();
    }
}
