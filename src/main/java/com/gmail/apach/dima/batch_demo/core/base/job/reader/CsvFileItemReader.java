package com.gmail.apach.dima.batch_demo.core.base.job.reader;

import com.gmail.apach.dima.batch_demo.core.base.common.constant.Delimiter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.core.io.Resource;

@Slf4j
public abstract class CsvFileItemReader<T> extends FlatFileItemReader<T> {

    protected LineMapper<T> lineMapper() {
        final var lineMapper = new DefaultLineMapper<T>();
        lineMapper.setLineTokenizer(lineTokenizer());
        lineMapper.setFieldSetMapper(fieldSetMapper());
        return lineMapper;
    }

    protected LineTokenizer lineTokenizer() {
        final var lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter(Delimiter.COMMA);
        lineTokenizer.setNames(getHeaders());
        return lineTokenizer;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        setLinesToSkip(getLinesToSkip());
        setLineMapper(lineMapper());
        super.afterPropertiesSet();
    }

    @Override
    protected void doOpen() throws Exception {
        setResource(getResource());
        super.doOpen();
    }

    protected abstract FieldSetMapper<T> fieldSetMapper();

    protected abstract int getLinesToSkip();

    protected abstract String[] getHeaders();

    protected abstract Resource getResource();
}
