package com.gmail.apach.dima.batch_demo.core.base.job.reader;

import org.springframework.batch.item.database.JpaCursorItemReader;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

public abstract class CursorItemReader<T> extends JpaCursorItemReader<T> {

    @Override
    public void afterPropertiesSet() throws Exception {
        final var factoryBean = new LocalContainerEntityManagerFactoryBean();
        super.setEntityManagerFactory(factoryBean.getObject());
        super.setQueryString(getQuery());
        super.afterPropertiesSet();
    }

    protected abstract String getQuery();
}
