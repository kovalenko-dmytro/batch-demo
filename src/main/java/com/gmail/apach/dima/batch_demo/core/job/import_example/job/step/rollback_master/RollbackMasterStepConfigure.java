package com.gmail.apach.dima.batch_demo.core.job.import_example.job.step.rollback_master;

import com.gmail.apach.dima.batch_demo.core.base.job.handler.JobExceptionHandler;
import com.gmail.apach.dima.batch_demo.core.base.job.listener.BaseStepExecutionListener;
import com.gmail.apach.dima.batch_demo.core.job.import_example.common.ImportExampleStepName;
import com.gmail.apach.dima.batch_demo.core.job.import_example.job.step.rollback_master.task.RollbackMasterTask;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@RequiredArgsConstructor
public class RollbackMasterStepConfigure {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;
    private final RollbackMasterTask rollbackMasterTask;
    private final BaseStepExecutionListener baseStepExecutionListener;
    private final JobExceptionHandler exceptionHandler;

    @Bean
    @SuppressWarnings("unused")
    protected Step rollbackMasterStep() {
        return new StepBuilder(ImportExampleStepName.TRUNCATE_WORK_STEP.getName(), jobRepository)
            .tasklet(rollbackMasterTask, transactionManager)
            .listener(baseStepExecutionListener)
            .exceptionHandler(exceptionHandler)
            .build();
    }
}
