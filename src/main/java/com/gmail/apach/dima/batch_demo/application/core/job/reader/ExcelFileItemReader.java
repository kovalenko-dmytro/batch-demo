package com.gmail.apach.dima.batch_demo.application.core.job.reader;

import org.springframework.batch.extensions.excel.RowMapper;
import org.springframework.batch.extensions.excel.poi.PoiItemReader;
import org.springframework.core.io.Resource;

public abstract class ExcelFileItemReader<T> extends PoiItemReader<T> {

    @Override
    public void afterPropertiesSet() throws Exception {
        setLinesToSkip(getLinesToSkip());
        setRowMapper(getRowMapper());
        super.afterPropertiesSet();
    }

    @Override
    protected void doOpen() throws Exception {
        setResource(getResource());
        super.doOpen();
    }

    protected abstract int getLinesToSkip();

    protected abstract RowMapper<T> getRowMapper();

    protected abstract Resource getResource();
}
