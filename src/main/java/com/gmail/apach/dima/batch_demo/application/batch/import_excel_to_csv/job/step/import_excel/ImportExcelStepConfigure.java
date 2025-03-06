package com.gmail.apach.dima.batch_demo.application.batch.import_excel_to_csv.job.step.import_excel;

import com.gmail.apach.dima.batch_demo.application.batch.import_excel_to_csv.common.ImportExcelToCsvStep;
import com.gmail.apach.dima.batch_demo.application.batch.import_excel_to_csv.job.step.import_excel.task.DownloadExcelTask;
import com.gmail.apach.dima.batch_demo.application.core.job.configure.BaseBatchConfigure;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ImportExcelStepConfigure {

    private final BaseBatchConfigure configure;
    private final DownloadExcelTask downloadExcelTask;

    @Bean
    @SuppressWarnings("unused")
    protected Step importExcelStep() {
        return new StepBuilder(ImportExcelToCsvStep.IMPORT_EXCEL_STEP.getName(), configure.getJobRepository())
            .tasklet(downloadExcelTask, configure.getPlatformTransactionManager())
            .listener(configure.getLogStepExecutionListener())
            .exceptionHandler(configure.getBaseJobExceptionHandler())
            .build();
    }
}
