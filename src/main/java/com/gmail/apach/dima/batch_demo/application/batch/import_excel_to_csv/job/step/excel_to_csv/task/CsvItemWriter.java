package com.gmail.apach.dima.batch_demo.application.batch.import_excel_to_csv.job.step.excel_to_csv.task;

import com.gmail.apach.dima.batch_demo.application.batch.import_excel_to_csv.common.CsvFileHeaders;
import com.gmail.apach.dima.batch_demo.application.batch.import_excel_to_csv.model.CsvLine;
import com.gmail.apach.dima.batch_demo.application.core.job.writer.CsvFileItemWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.WritableResource;
import org.springframework.stereotype.Component;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@RequiredArgsConstructor
public class CsvItemWriter extends CsvFileItemWriter<CsvLine> {

    @Override
    protected String[] getHeaders() {
        return CsvFileHeaders.headers;
    }

    @Override
    protected WritableResource getResource() {
        return new FileSystemResource("target/test-outputs/output.txt");
    }
}
