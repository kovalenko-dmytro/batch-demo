package com.gmail.apach.dima.batch_demo.application.core.job.writer;

import com.gmail.apach.dima.batch_demo.infrastructure.common.constant.Delimiter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.batch.item.file.transform.LineAggregator;
import org.springframework.core.io.WritableResource;

@Slf4j
public abstract class CsvFileItemWriter<T> extends FlatFileItemWriter<T> {

    @Override
    public void afterPropertiesSet() throws Exception {
        setResource(getResource());
        setShouldDeleteIfExists(true);
        setAppendAllowed(true);
        setLineAggregator(lineAggregator());
        super.afterPropertiesSet();
    }

    protected LineAggregator<T> lineAggregator() {
        return new DelimitedLineAggregator<>() {
            {
                setDelimiter(Delimiter.COMMA);
                setFieldExtractor(new BeanWrapperFieldExtractor<>() {
                    {
                        setNames(getHeaders());
                    }
                });
            }
        };
    }

    protected abstract String[] getHeaders();

    protected abstract WritableResource getResource();
}
