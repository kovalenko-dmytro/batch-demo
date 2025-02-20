package com.gmail.apach.dima.batch_demo.core.batch.import_csv_to_db.job.step.truncate_work;

import com.gmail.apach.dima.batch_demo.core.base.job.handler.BaseJobExceptionHandler;
import com.gmail.apach.dima.batch_demo.core.base.job.listener.LogStepExecutionListener;
import com.gmail.apach.dima.batch_demo.core.batch.import_csv_to_db.common.ImportCsvToDbStep;
import com.gmail.apach.dima.batch_demo.core.batch.import_csv_to_db.job.step.truncate_work.task.TruncateWorkTask;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@RequiredArgsConstructor
public class TruncateWorkStepConfigure {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;
    private final BaseJobExceptionHandler exceptionHandler;
    private final TruncateWorkTask truncateWorkTask;
    private final LogStepExecutionListener logStepExecutionListener;

    @Bean
    @SuppressWarnings("unused")
    protected Step truncateWorkStep() {
        return new StepBuilder(ImportCsvToDbStep.TRUNCATE_WORK_STEP.getName(), jobRepository)
            .tasklet(truncateWorkTask, transactionManager)
            .listener(logStepExecutionListener)
            .exceptionHandler(exceptionHandler)
            .build();
    }
}
