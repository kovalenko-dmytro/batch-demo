package com.gmail.apach.dima.batch_demo.core.job.import_example.writer;

import com.gmail.apach.dima.batch_demo.infrastructure.adapter.output.db.inventory.entity.ExampleEntity;
import com.gmail.apach.dima.batch_demo.infrastructure.adapter.output.db.inventory.repository.ExampleRepository;
import com.gmail.apach.dima.batch_demo.infrastructure.common.message.MessageUtil;
import com.gmail.apach.dima.batch_demo.infrastructure.common.message.code.Info;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.batch.core.ChunkListener;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@RequiredArgsConstructor
public class ExampleWriter
    implements ItemWriter<ExampleEntity>, StepExecutionListener, ChunkListener {

    private final ExampleRepository exampleRepository;
    private final MessageUtil messageUtil;

    private String jobName;
    private long jobId;
    private String stepName;
    private List<String> insertedIds;

    @Override
    public void beforeStep(@NonNull StepExecution stepExecution) {
        stepExecution.getExecutionContext().put("inserted-ids", new ArrayList<>());

        final var jobInstance = stepExecution.getJobExecution().getJobInstance();
        this.jobName = jobInstance.getJobName();
        this.jobId = jobInstance.getInstanceId();
        this.stepName = stepExecution.getStepName();

        log.info(messageUtil.getMessage(Info.JOB_STEP_STARTED, jobName, jobId, stepName));
    }

    @Override
    public void write(@NonNull Chunk<? extends ExampleEntity> chunk) {
        final var inserted = exampleRepository.saveAll(chunk.getItems());
        insertedIds.addAll(inserted.stream().map(ExampleEntity::getId).toList());
        log.info(messageUtil.getMessage(Info.JOB_STEP_WRITER_PROCESSED, jobName, jobId, stepName, inserted.size()));
    }

    @Override
    @SuppressWarnings("unchecked")
    public void beforeChunk(@NonNull ChunkContext context) {
        this.insertedIds = (List<String>) context
            .getStepContext()
            .getStepExecution()
            .getExecutionContext()
            .get("inserted-ids");
    }

    @Override
    public void afterChunkError(ChunkContext context) {
        context
            .getStepContext()
            .getStepExecution().setExitStatus(ExitStatus.FAILED);
    }

    @Override
    public void afterChunk(@NonNull ChunkContext context) {
        context
            .getStepContext()
            .getStepExecution()
            .getExecutionContext()
            .put("inserted-ids", insertedIds);
    }

    @NonNull
    @SuppressWarnings("unchecked")
    @Override
    public ExitStatus afterStep(@NonNull StepExecution stepExecution) {
        final var exceptions = stepExecution.getFailureExceptions();

        if (exceptions.isEmpty()) {
            log.info(messageUtil.getMessage(
                Info.JOB_STEP_COMPLETED,
                jobName, jobId, stepName, ExitStatus.COMPLETED.getExitCode()));
            return ExitStatus.COMPLETED;
        }

        this.insertedIds = (List<String>) stepExecution.getExecutionContext().get("inserted-ids");
        if (CollectionUtils.isNotEmpty(insertedIds)) {
            exampleRepository.deleteAllById(insertedIds);
            log.info(messageUtil.getMessage(
                Info.JOB_STEP_WRITER_ROLLBACK,
                jobName, jobId, stepName, insertedIds.size()));
        }
        return ExitStatus.FAILED;
    }
}
