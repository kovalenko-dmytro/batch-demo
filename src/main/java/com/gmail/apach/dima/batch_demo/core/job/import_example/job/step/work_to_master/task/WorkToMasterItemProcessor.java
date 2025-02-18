package com.gmail.apach.dima.batch_demo.core.job.import_example.job.step.work_to_master.task;

import com.gmail.apach.dima.batch_demo.core.job.import_example.job.step.work_to_master.mapper.ExampleMapper;
import com.gmail.apach.dima.batch_demo.infrastructure.adapter.output.db.example.entity.MasterExampleEntity;
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
public class WorkToMasterItemProcessor implements ItemProcessor<WorkExampleEntity, MasterExampleEntity> {

    private final ExampleMapper exampleMapper;

    @NonNull
    @Override
    public MasterExampleEntity process(@NonNull WorkExampleEntity work) {
        return exampleMapper.toExampleEntity(work);
    }
}
