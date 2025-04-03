package com.gmail.apach.dima.batch_demo.worker.application.core.configure;

import com.gmail.apach.dima.batch_demo.worker.application.core.handler.BaseJobExceptionHandler;
import com.gmail.apach.dima.batch_demo.worker.application.core.listener.LogJobFailuresListener;
import com.gmail.apach.dima.batch_demo.worker.application.core.listener.LogStepExecutionListener;
import com.gmail.apach.dima.batch_demo.worker.application.core.validator.FileResourceValidator;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@RequiredArgsConstructor
@Getter
public class BaseBatchConfigure {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager platformTransactionManager;
    private final BatchConfigProperties batchConfigProperties;
    private final BaseJobExceptionHandler baseJobExceptionHandler;
    private final LogStepExecutionListener logStepExecutionListener;
    private final LogJobFailuresListener logJobFailuresListener;
    private final FileResourceValidator fileResourceValidator;

}
