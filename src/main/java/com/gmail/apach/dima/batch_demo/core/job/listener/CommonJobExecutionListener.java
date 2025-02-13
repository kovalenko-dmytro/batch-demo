package com.gmail.apach.dima.batch_demo.core.job.listener;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CommonJobExecutionListener implements JobExecutionListener {

    @Override
    public void afterJob(JobExecution jobExecution) {
        final var allFailureExceptions = jobExecution.getAllFailureExceptions();
        if (CollectionUtils.isNotEmpty(allFailureExceptions)) {
            jobExecution.setStatus(BatchStatus.FAILED);
        }
    }
}
