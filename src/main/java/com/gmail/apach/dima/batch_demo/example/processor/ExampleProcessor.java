package com.gmail.apach.dima.batch_demo.example.processor;

import com.gmail.apach.dima.batch_demo.domain.entity.ExampleEntity;
import com.gmail.apach.dima.batch_demo.example.mapper.ExampleMapper;
import com.gmail.apach.dima.batch_demo.example.model.ExampleLine;
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
public class ExampleProcessor implements ItemProcessor<ExampleLine, ExampleEntity> {

    private final ExampleMapper exampleMapper;

    @NonNull
    @Override
    public ExampleEntity process(@NonNull ExampleLine line) {
        return exampleMapper.toExampleEntity(line);
    }
}
