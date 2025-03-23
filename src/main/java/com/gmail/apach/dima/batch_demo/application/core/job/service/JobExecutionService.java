package com.gmail.apach.dima.batch_demo.application.core.job.service;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JobExecutionService {

    private final JobExplorer jobExplorer;

    public Optional<JobExecution> getLastJobExecution(String jobName) {
        return Optional
            .ofNullable(jobName)
            .map(job -> jobExplorer.getLastJobInstance(jobName))
            .map(jobExplorer::getLastJobExecution);
    }
}
