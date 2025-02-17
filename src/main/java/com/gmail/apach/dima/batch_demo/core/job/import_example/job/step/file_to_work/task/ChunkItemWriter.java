package com.gmail.apach.dima.batch_demo.core.job.import_example.job.step.file_to_work.task;

import com.gmail.apach.dima.batch_demo.application.output.db.WorkExampleOutputPort;
import com.gmail.apach.dima.batch_demo.infrastructure.adapter.output.db.example.entity.WorkExampleEntity;
import com.gmail.apach.dima.batch_demo.infrastructure.common.message.MessageUtil;
import com.gmail.apach.dima.batch_demo.infrastructure.common.message.code.Info;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
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
public class ChunkItemWriter
    implements ItemWriter<WorkExampleEntity>, StepExecutionListener {

    private final WorkExampleOutputPort workExampleOutputPort;
    private final MessageUtil messageUtil;

    private List<String> insertedIds;

    @Override
    public void beforeStep(@NonNull StepExecution stepExecution) {
        this.insertedIds = new ArrayList<>();
    }

    @Override
    @SuppressWarnings("unchecked")
    public void write(@NonNull Chunk<? extends WorkExampleEntity> chunk) {
        final var inserted = workExampleOutputPort.save((List<WorkExampleEntity>) chunk.getItems());
        insertedIds.addAll(inserted.stream().map(WorkExampleEntity::getId).toList());
    }

    @NonNull
    @Override
    public ExitStatus afterStep(@NonNull StepExecution stepExecution) {
        final var exceptions = stepExecution.getFailureExceptions();

        if (exceptions.isEmpty()) {
            return ExitStatus.COMPLETED;
        }

        if (CollectionUtils.isNotEmpty(insertedIds)) {
            workExampleOutputPort.delete(insertedIds);
            final var jobInstance = stepExecution.getJobExecution().getJobInstance();
            log.info(messageUtil.getMessage(
                Info.JOB_STEP_WRITER_ROLLBACK,
                jobInstance.getJobName(),
                jobInstance.getInstanceId(),
                stepExecution.getStepName(), insertedIds.size()));
        }
        return ExitStatus.FAILED;
    }
}
