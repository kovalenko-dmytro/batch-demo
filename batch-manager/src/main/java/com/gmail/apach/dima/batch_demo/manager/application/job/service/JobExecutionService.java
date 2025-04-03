package com.gmail.apach.dima.batch_demo.manager.application.job.service;

import com.gmail.apach.dima.batch_demo.common.model.RequestParameters;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JobExecutionService {

    private final JobExplorer jobExplorer;

    public Optional<JobExecution> getLastJobExecution(String jobName) {
        return Optional
            .ofNullable(jobName)
            .map(job -> jobExplorer.getLastJobInstance(jobName))
            .map(jobExplorer::getLastJobExecution);
    }

    public List<JobExecution> getJobExecutionsWithParameters(String jobName, RequestParameters parameters) {
        return Optional
            .ofNullable(jobName)
            .map(job -> jobExplorer.getJobInstance(jobName, parameters.toJobParameters()))
            .map(jobExplorer::getJobExecutions)
            .orElse(Collections.emptyList());
    }
}
