package com.gmail.apach.dima.batch_demo.application.batch.import_csv_to_db.job.step.work_to_master.task;

import com.gmail.apach.dima.batch_demo.application.core.job.reader.JpaCursorDbItemReader;
import com.gmail.apach.dima.batch_demo.infrastructure.adapter.output.db.work.entity.WorkTableEntity;
import jakarta.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@RequiredArgsConstructor
public class WorkItemReader extends JpaCursorDbItemReader<WorkTableEntity> {

    private final EntityManagerFactory entityManagerFactory;

    @Override
    protected String getQuery() {
        return "SELECT wte FROM WorkTableEntity wte";
    }

    @Override
    protected EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }
}
