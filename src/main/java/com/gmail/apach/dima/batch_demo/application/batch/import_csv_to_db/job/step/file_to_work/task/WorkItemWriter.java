package com.gmail.apach.dima.batch_demo.application.batch.import_csv_to_db.job.step.file_to_work.task;

import com.gmail.apach.dima.batch_demo.application.batch.import_csv_to_db.model.WorkModel;
import com.gmail.apach.dima.batch_demo.port.output.db.WorkTableOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@RequiredArgsConstructor
public class WorkItemWriter implements ItemWriter<WorkModel> {

    private final WorkTableOutputPort workTableOutputPort;

    @Override
    @SuppressWarnings("unchecked")
    public void write(@NonNull Chunk<? extends WorkModel> chunk) {
        workTableOutputPort.save((List<WorkModel>) chunk.getItems());
    }
}
