package com.gmail.apach.dima.batch_demo.application.batch.import_excel_to_csv.job.step.excel_to_csv.task;

import com.gmail.apach.dima.batch_demo.application.batch.import_excel_to_csv.common.ExcelFileHeaders;
import com.gmail.apach.dima.batch_demo.application.batch.import_excel_to_csv.model.ExcelLine;
import com.gmail.apach.dima.batch_demo.application.core.file.model.StoredResource;
import com.gmail.apach.dima.batch_demo.application.core.job.constant.JobExecutionContextKey;
import com.gmail.apach.dima.batch_demo.application.core.job.reader.ExcelFileItemReader;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.extensions.excel.RowMapper;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@RequiredArgsConstructor
public class ExcelItemReader extends ExcelFileItemReader<ExcelLine> implements StepExecutionListener {

    private StoredResource storedResource;

    @Override
    public void beforeStep(@NonNull StepExecution stepExecution) {
        this.storedResource = (StoredResource) stepExecution
            .getJobExecution()
            .getExecutionContext()
            .get(JobExecutionContextKey.STORED_RESOURCE);
    }

    @Override
    protected int getLinesToSkip() {
        return 1;
    }

    @Override
    protected RowMapper<ExcelLine> getRowMapper() {
        return rowSet -> ExcelLine.builder()
            .firstName(rowSet.getProperties().getProperty(ExcelFileHeaders.FIRST_NAME.getName()))
            .lastName(rowSet.getProperties().getProperty(ExcelFileHeaders.LAST_NAME.getName()))
            .age(Integer.valueOf(rowSet.getProperties().getProperty(ExcelFileHeaders.AGE.getName())))
            .active(Boolean.valueOf(rowSet.getProperties().getProperty(ExcelFileHeaders.ACTIVE.getName())))
            .build();
    }

    @Override
    protected Resource getResource() {
        return new InputStreamResource(new ByteArrayInputStream(storedResource.getPayload()));
    }
}
