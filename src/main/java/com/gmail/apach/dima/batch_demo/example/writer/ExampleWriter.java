package com.gmail.apach.dima.batch_demo.example.writer;

import com.gmail.apach.dima.batch_demo.core.config.message.constant.Info;
import com.gmail.apach.dima.batch_demo.core.service.message.MessageService;
import com.gmail.apach.dima.batch_demo.domain.entity.ExampleEntity;
import com.gmail.apach.dima.batch_demo.domain.repository.ExampleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@RequiredArgsConstructor
public class ExampleWriter implements ItemWriter<ExampleEntity>, StepExecutionListener {

    private final ExampleRepository exampleRepository;
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
        log.info(messageService.message(Info.JOB_STEP_WRITER_STARTED, jobName, jobId, stepName));
    }

    @Override
    public void write(@NonNull Chunk<? extends ExampleEntity> chunk) {
        final var inserted = exampleRepository.saveAll(chunk.getItems());
        log.info(messageService.message(Info.JOB_STEP_WRITER_PROCESSED, jobName, jobId, stepName, inserted.size()));
    }

    @NonNull
    @Override
    public ExitStatus afterStep(@NonNull StepExecution stepExecution) {
        log.info(messageService.message(Info.JOB_STEP_WRITER_COMPLETED, jobName, jobId, stepName));
        return StepExecutionListener.super.afterStep(stepExecution);
    }
}
