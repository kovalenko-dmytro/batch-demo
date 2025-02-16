package com.gmail.apach.dima.batch_demo.core.job.import_example.job.step.file_to_work.task.reader;

import com.gmail.apach.dima.batch_demo.core.base.common.constant.Format;
import com.gmail.apach.dima.batch_demo.core.base.common.constant.JobExecutionContextKey;
import com.gmail.apach.dima.batch_demo.core.base.common.util.DateUtil;
import com.gmail.apach.dima.batch_demo.core.base.job.reader.CsvFileItemReader;
import com.gmail.apach.dima.batch_demo.core.job.import_example.common.FileHeaders;
import com.gmail.apach.dima.batch_demo.core.job.import_example.model.WorkLine;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.Resource;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@RequiredArgsConstructor
public class ExampleReader extends CsvFileItemReader<WorkLine> implements StepExecutionListener {

    private Resource jobResource;

    @Override
    public void beforeStep(@NonNull StepExecution stepExecution) {
        this.jobResource = (Resource) stepExecution
            .getJobExecution()
            .getExecutionContext()
            .get(JobExecutionContextKey.JOB_FILE_RESOURCE);
    }

    @Override
    protected FieldSetMapper<WorkLine> fieldSetMapper() {
        return fieldSet -> WorkLine.builder()
            .stringColumn(fieldSet.readString(FileHeaders.HEADER_STRING.getValue()))
            .integerColumn(fieldSet.readInt(FileHeaders.HEADER_INTEGER.getValue()))
            .dateTimeColumn(DateUtil.toLocalDateTime(
                    fieldSet.readDate(
                        FileHeaders.HEADER_DATE_TIME.getValue(),
                        Format.DATE_TIME_yyyy_MM_dd_HH_mm_ss)
                )
            )
            .booleanColumn(fieldSet.readBoolean(FileHeaders.HEADER_BOOLEAN.getValue()))
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
        return this.jobResource;
    }
}
