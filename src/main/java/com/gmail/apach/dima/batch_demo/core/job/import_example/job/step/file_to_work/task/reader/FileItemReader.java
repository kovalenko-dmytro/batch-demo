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
public class FileItemReader extends CsvFileItemReader<WorkLine> implements StepExecutionListener {

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
            .fieldParam1(fieldSet.readString(FileHeaders.FIELD_PARAM_1.getName()))
            .fieldParam2(fieldSet.readInt(FileHeaders.FIELD_PARAM_2.getName()))
            .fieldParam3(
                DateUtil.toLocalDateTime(
                    fieldSet.readDate(FileHeaders.FIELD_PARAM_3.getName(),
                        Format.DATE_TIME_yyyy_MM_dd_HH_mm_ss)))
            .fieldParam4(fieldSet.readBoolean(FileHeaders.FIELD_PARAM_4.getName()))
            .fieldParam5(fieldSet.readString(FileHeaders.FIELD_PARAM_5.getName()))
            .fieldParam6(fieldSet.readString(FileHeaders.FIELD_PARAM_6.getName()))
            .fieldParam7(fieldSet.readInt(FileHeaders.FIELD_PARAM_7.getName()))
            .fieldParam8(fieldSet.readString(FileHeaders.FIELD_PARAM_8.getName()))
            .fieldParam9(fieldSet.readInt(FileHeaders.FIELD_PARAM_9.getName()))
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
