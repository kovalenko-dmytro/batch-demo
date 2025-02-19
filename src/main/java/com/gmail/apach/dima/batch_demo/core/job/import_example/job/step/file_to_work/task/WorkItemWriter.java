package com.gmail.apach.dima.batch_demo.core.job.import_example.job.step.file_to_work.task;

import com.gmail.apach.dima.batch_demo.application.output.db.WorkExampleOutputPort;
import com.gmail.apach.dima.batch_demo.infrastructure.adapter.output.db.example.entity.WorkExampleEntity;
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
public class WorkItemWriter implements ItemWriter<WorkExampleEntity> {

    private final WorkExampleOutputPort workExampleOutputPort;

    @Override
    public void write(@NonNull Chunk<? extends WorkExampleEntity> chunk) {
        final var items = chunk.getItems().stream()
            .map(item -> (WorkExampleEntity) item)
            .toList();
        workExampleOutputPort.save(items);
    }
}
