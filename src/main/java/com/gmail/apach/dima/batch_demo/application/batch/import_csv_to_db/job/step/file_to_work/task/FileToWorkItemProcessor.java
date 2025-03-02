package com.gmail.apach.dima.batch_demo.application.batch.import_csv_to_db.job.step.file_to_work.task;

import com.gmail.apach.dima.batch_demo.application.batch.import_csv_to_db.mapper.WorkMapper;
import com.gmail.apach.dima.batch_demo.application.batch.import_csv_to_db.model.WorkModel;
import com.gmail.apach.dima.batch_demo.infrastructure.adapter.output.db.work.entity.WorkTableEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@RequiredArgsConstructor
public class FileToWorkItemProcessor implements ItemProcessor<WorkModel, WorkTableEntity> {

    private final WorkMapper workMapper;

    @NonNull
    @Override
    public WorkTableEntity process(@NonNull WorkModel workModel) {
        return workMapper.toWorkTableEntity(workModel);
    }
}
