package com.gmail.apach.dima.batch_demo.core.job.import_example.job.step.file_to_work.task;

import com.gmail.apach.dima.batch_demo.core.base.common.constant.Format;
import com.gmail.apach.dima.batch_demo.core.base.common.util.DateUtil;
import com.gmail.apach.dima.batch_demo.core.base.job.constant.JobExecutionContextKey;
import com.gmail.apach.dima.batch_demo.core.base.job.reader.CsvFileItemReader;
import com.gmail.apach.dima.batch_demo.core.base.model.oss.StoredResource;
import com.gmail.apach.dima.batch_demo.core.job.import_example.common.FileHeaders;
import com.gmail.apach.dima.batch_demo.core.job.import_example.model.WorkLine;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
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
public class FileItemReader extends CsvFileItemReader<WorkLine> implements StepExecutionListener {

    private StoredResource storedResource;

    @Override
    public void beforeStep(@NonNull StepExecution stepExecution) {
        this.storedResource = (StoredResource) stepExecution
            .getJobExecution()
            .getExecutionContext()
            .get(JobExecutionContextKey.STORED_RESOURCE);
    }

    @Override
    protected FieldSetMapper<WorkLine> fieldSetMapper() {
        return fieldSet -> WorkLine.builder()
            .fieldParam1(fieldSet.readString(FileHeaders.FIELD_PARAM_1.getName()))
            .fieldParam2(fieldSet.readInt(FileHeaders.FIELD_PARAM_2.getName()))
            .fieldParam3(
                DateUtil.toLocalDateTime(
                    fieldSet.readDate(FileHeaders.FIELD_PARAM_3.getName(),
                        Format.DATE_TIME_yyyy_MM_dd_HH_mm_ss)))
            .fieldParam4(fieldSet.readBoolean(FileHeaders.FIELD_PARAM_4.getName()))
            .build();
    }

    @Override
    protected int getLinesToSkip() {
        return 1;
    }

    @Override
    protected String[] getHeaders() {
        return FileHeaders.headers;
    }

    @Override
    protected Resource getResource() {
        return new InputStreamResource(new ByteArrayInputStream(storedResource.getPayload()));
    }
}
