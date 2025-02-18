package com.gmail.apach.dima.batch_demo.core.job.import_example.job.step.work_to_master.task;

import com.gmail.apach.dima.batch_demo.application.output.db.MasterExampleOutputPort;
import com.gmail.apach.dima.batch_demo.core.base.job.constant.JobExecutionContextKey;
import com.gmail.apach.dima.batch_demo.infrastructure.adapter.output.db.example.entity.MasterExampleEntity;
import lombok.RequiredArgsConstructor;
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

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@RequiredArgsConstructor
public class MasterItemWriter
    implements ItemWriter<MasterExampleEntity>, StepExecutionListener {

    private final MasterExampleOutputPort masterExampleOutputPort;

    private StepExecution stepExecution;

    @Override
    public void beforeStep(@NonNull StepExecution stepExecution) {
        stepExecution.getExecutionContext()
            .put(JobExecutionContextKey.INSERTED_IDS, new ArrayList<String>());
        this.stepExecution = stepExecution;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void write(@NonNull Chunk<? extends MasterExampleEntity> chunk) {
        final var items = chunk.getItems().stream()
            .map(item -> (MasterExampleEntity) item)
            .toList();
        final var inserted = masterExampleOutputPort.save(items);
        final var insertedIds = inserted.stream().map(MasterExampleEntity::getId).toList();

        final var stepContext = this.stepExecution.getExecutionContext();
        final var heldIds = (List<String>) stepContext.get(JobExecutionContextKey.INSERTED_IDS);
        heldIds.addAll(insertedIds);
    }
}
