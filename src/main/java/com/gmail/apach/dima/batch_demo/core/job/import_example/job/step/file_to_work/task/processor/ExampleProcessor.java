package com.gmail.apach.dima.batch_demo.core.job.import_example.job.step.file_to_work.task.processor;

import com.gmail.apach.dima.batch_demo.core.job.import_example.mapper.ExampleMapper;
import com.gmail.apach.dima.batch_demo.core.job.import_example.model.WorkLine;
import com.gmail.apach.dima.batch_demo.infrastructure.adapter.output.db.example.entity.ExampleEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@RequiredArgsConstructor
public class ExampleProcessor implements ItemProcessor<WorkLine, ExampleEntity> {

    private final ExampleMapper exampleMapper;

    @NonNull
    @Override
    public ExampleEntity process(@NonNull WorkLine line) {
        return exampleMapper.toWorkExampleEntity(line);
    }
}
