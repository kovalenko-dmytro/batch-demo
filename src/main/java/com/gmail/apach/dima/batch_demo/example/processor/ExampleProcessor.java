package com.gmail.apach.dima.batch_demo.example.processor;

import com.gmail.apach.dima.batch_demo.core.config.message.constant.Info;
import com.gmail.apach.dima.batch_demo.core.service.message.MessageService;
import com.gmail.apach.dima.batch_demo.domain.entity.ExampleEntity;
import com.gmail.apach.dima.batch_demo.example.mapper.ExampleMapper;
import com.gmail.apach.dima.batch_demo.example.model.ExampleLine;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@RequiredArgsConstructor
public class ExampleProcessor implements ItemProcessor<ExampleLine, ExampleEntity>, StepExecutionListener {

    private final ExampleMapper exampleMapper;
    private final MessageService messageService;

    private String jobName;
    private long jobId;
    private String stepName;

    @Override
    public void beforeStep(@NonNull StepExecution stepExecution) {
        final var jobInstance = stepExecution.getJobExecution().getJobInstance();
        this.jobName = jobInstance.getJobName();
        this.jobId = jobInstance.getInstanceId();
        this.stepName = stepExecution.getStepName();
        log.info(messageService.message(Info.JOB_STEP_PROCESSOR_STARTED, jobName, jobId, stepName));
    }

    @NonNull
    @Override
    public ExampleEntity process(@NonNull ExampleLine line) {
        final var processed = exampleMapper.toExampleEntity(line);
        log.info(messageService.message(Info.JOB_STEP_PROCESSOR_PROCESSED, jobName, jobId, stepName));
        return processed;
    }

    @NonNull
    @Override
    public ExitStatus afterStep(@NonNull StepExecution stepExecution) {
        log.info(messageService.message(Info.JOB_STEP_PROCESSOR_COMPLETED, jobName, jobId, stepName));
        return StepExecutionListener.super.afterStep(stepExecution);
    }
}
