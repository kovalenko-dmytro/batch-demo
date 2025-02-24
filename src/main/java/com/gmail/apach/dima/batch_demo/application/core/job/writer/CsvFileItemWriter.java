package com.gmail.apach.dima.batch_demo.application.core.job.writer;

import com.gmail.apach.dima.batch_demo.infrastructure.common.constant.Delimiter;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.file.FlatFileHeaderCallback;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.batch.item.file.transform.LineAggregator;
import org.springframework.core.io.WritableResource;
import org.springframework.lang.NonNull;

public abstract class CsvFileItemWriter<T> extends FlatFileItemWriter<T> {

    @Override
    public void afterPropertiesSet() throws Exception {
        setShouldDeleteIfExists(true);
        setAppendAllowed(true);
        setLineAggregator(lineAggregator());
        setHeaderCallback(getHeaderCallBack());
        super.afterPropertiesSet();
    }

    @Override
    public void open(@NonNull ExecutionContext executionContext) throws ItemStreamException {
        setResource(getResource());
        super.open(executionContext);
    }

    private FlatFileHeaderCallback getHeaderCallBack() {
        return writer -> writer.write(getHeadersRow());
    }

    private LineAggregator<T> lineAggregator() {
        return new DelimitedLineAggregator<>() {
            {
                setDelimiter(Delimiter.COMMA);
                setFieldExtractor(new BeanWrapperFieldExtractor<>() {
                    {
                        setNames(getFieldNames());
                    }
                });
            }
        };
    }

    protected abstract String[] getFieldNames();

    protected abstract String getHeadersRow();

    protected abstract WritableResource getResource();
}
