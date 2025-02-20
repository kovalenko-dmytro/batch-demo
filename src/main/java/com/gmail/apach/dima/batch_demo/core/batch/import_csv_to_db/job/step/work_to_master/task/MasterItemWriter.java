package com.gmail.apach.dima.batch_demo.core.batch.import_csv_to_db.job.step.work_to_master.task;

import com.gmail.apach.dima.batch_demo.application.output.db.MasterTableOutputPort;
import com.gmail.apach.dima.batch_demo.core.base.job.constant.JobExecutionContextKey;
import com.gmail.apach.dima.batch_demo.infrastructure.adapter.output.db.master.entity.MasterTableEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ExecutionContext;
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
    implements ItemWriter<MasterTableEntity>, StepExecutionListener {

    private final MasterTableOutputPort masterTableOutputPort;

    private ExecutionContext executionContext;

    @Override
    public void beforeStep(@NonNull StepExecution stepExecution) {
        final var executionContext = stepExecution.getJobExecution().getExecutionContext();
        executionContext.put(JobExecutionContextKey.INSERTED_IDS, new ArrayList<String>());
        this.executionContext = executionContext;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void write(@NonNull Chunk<? extends MasterTableEntity> chunk) {
        final var items = chunk.getItems().stream()
            .map(item -> (MasterTableEntity) item)
            .toList();
        final var inserted = masterTableOutputPort.save(items);
        final var insertedIds = inserted.stream().map(MasterTableEntity::getId).toList();

        final var heldIds = (List<String>) this.executionContext.get(JobExecutionContextKey.INSERTED_IDS);
        heldIds.addAll(insertedIds);
    }
}
