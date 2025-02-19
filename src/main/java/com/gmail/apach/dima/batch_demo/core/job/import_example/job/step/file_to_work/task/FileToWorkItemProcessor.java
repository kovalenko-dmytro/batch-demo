package com.gmail.apach.dima.batch_demo.core.job.import_example.job.step.file_to_work.task;

import com.gmail.apach.dima.batch_demo.core.job.import_example.mapper.WorkExampleMapper;
import com.gmail.apach.dima.batch_demo.core.job.import_example.model.WorkLine;
import com.gmail.apach.dima.batch_demo.infrastructure.adapter.output.db.example.entity.WorkExampleEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@RequiredArgsConstructor
public class FileToWorkItemProcessor implements ItemProcessor<WorkLine, WorkExampleEntity> {

    private final WorkExampleMapper workExampleMapper;

    @NonNull
    @Override
    public WorkExampleEntity process(@NonNull WorkLine line) {
        return workExampleMapper.toWorkExampleEntity(line);
    }
}
