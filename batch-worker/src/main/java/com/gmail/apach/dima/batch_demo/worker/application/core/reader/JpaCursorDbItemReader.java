package com.gmail.apach.dima.batch_demo.worker.application.core.reader;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.batch.item.database.JpaCursorItemReader;

public abstract class JpaCursorDbItemReader<T> extends JpaCursorItemReader<T> {

    @Override
    public void afterPropertiesSet() throws Exception {
        super.setEntityManagerFactory(getEntityManagerFactory());
        super.setQueryString(getQuery());
        super.afterPropertiesSet();
    }

    protected abstract String getQuery();

    protected abstract EntityManagerFactory getEntityManagerFactory();
}
