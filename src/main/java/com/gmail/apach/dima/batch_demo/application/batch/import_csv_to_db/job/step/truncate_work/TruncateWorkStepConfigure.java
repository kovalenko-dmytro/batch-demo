package com.gmail.apach.dima.batch_demo.application.batch.import_csv_to_db.job.step.truncate_work;

import com.gmail.apach.dima.batch_demo.application.batch.import_csv_to_db.common.ImportCsvToDbStep;
import com.gmail.apach.dima.batch_demo.application.batch.import_csv_to_db.job.step.truncate_work.task.TruncateWorkTask;
import com.gmail.apach.dima.batch_demo.application.core.job.configure.BaseBatchConfigure;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class TruncateWorkStepConfigure {

    private final BaseBatchConfigure configure;
    private final TruncateWorkTask truncateWorkTask;

    @Bean
    @SuppressWarnings("unused")
    protected Step truncateWorkStep() {
        return new StepBuilder(ImportCsvToDbStep.TRUNCATE_WORK_STEP.getName(), configure.getJobRepository())
            .tasklet(truncateWorkTask, configure.getPlatformTransactionManager())
            .listener(configure.getLogStepExecutionListener())
            .exceptionHandler(configure.getBaseJobExceptionHandler())
            .build();
    }
}
