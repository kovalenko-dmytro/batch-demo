package com.gmail.apach.dima.batch_demo.core.job.import_example.job.step.work_to_master.task;

import com.gmail.apach.dima.batch_demo.core.base.job.reader.JpaCursorDbItemReader;
import com.gmail.apach.dima.batch_demo.infrastructure.adapter.output.db.example.entity.WorkExampleEntity;
import jakarta.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@RequiredArgsConstructor
public class WorkItemReader extends JpaCursorDbItemReader<WorkExampleEntity> {

    private final EntityManagerFactory entityManagerFactory;

    @Override
    protected String getQuery() {
        return "SELECT we FROM WorkExampleEntity we";
    }

    @Override
    protected EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }
}
