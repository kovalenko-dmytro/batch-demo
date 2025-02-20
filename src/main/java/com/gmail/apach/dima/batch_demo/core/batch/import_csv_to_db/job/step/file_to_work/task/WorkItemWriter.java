package com.gmail.apach.dima.batch_demo.core.batch.import_csv_to_db.job.step.file_to_work.task;

import com.gmail.apach.dima.batch_demo.application.output.db.WorkTableOutputPort;
import com.gmail.apach.dima.batch_demo.infrastructure.adapter.output.db.work.entity.WorkTableEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@RequiredArgsConstructor
public class WorkItemWriter implements ItemWriter<WorkTableEntity> {

    private final WorkTableOutputPort workTableOutputPort;

    @Override
    public void write(@NonNull Chunk<? extends WorkTableEntity> chunk) {
        final var items = chunk.getItems().stream()
            .map(item -> (WorkTableEntity) item)
            .toList();
        workTableOutputPort.save(items);
    }
}
