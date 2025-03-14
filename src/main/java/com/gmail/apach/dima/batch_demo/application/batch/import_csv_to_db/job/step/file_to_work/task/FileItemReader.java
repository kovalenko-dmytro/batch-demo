package com.gmail.apach.dima.batch_demo.application.batch.import_csv_to_db.job.step.file_to_work.task;

import com.gmail.apach.dima.batch_demo.application.batch.import_csv_to_db.common.ImportCsvToDbFileHeaders;
import com.gmail.apach.dima.batch_demo.application.batch.import_csv_to_db.model.CsvLineModel;
import com.gmail.apach.dima.batch_demo.application.core.file.model.StoredResource;
import com.gmail.apach.dima.batch_demo.application.core.job.constant.JobExecutionContextKey;
import com.gmail.apach.dima.batch_demo.application.core.job.reader.CsvFileItemReader;
import com.gmail.apach.dima.batch_demo.common.constant.Format;
import com.gmail.apach.dima.batch_demo.common.util.DateUtil;
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
public class FileItemReader extends CsvFileItemReader<CsvLineModel> implements StepExecutionListener {

    private StoredResource storedResource;

    @Override
    public void beforeStep(@NonNull StepExecution stepExecution) {
        this.storedResource = (StoredResource) stepExecution
            .getJobExecution()
            .getExecutionContext()
            .get(JobExecutionContextKey.STORED_RESOURCE);
    }

    @Override
    protected FieldSetMapper<CsvLineModel> fieldSetMapper() {
        return fieldSet -> CsvLineModel.builder()
            .fieldParam1(fieldSet.readString(ImportCsvToDbFileHeaders.FIELD_PARAM_1.getName()))
            .fieldParam2(fieldSet.readInt(ImportCsvToDbFileHeaders.FIELD_PARAM_2.getName()))
            .fieldParam3(
                DateUtil.toLocalDateTime(
                    fieldSet.readDate(ImportCsvToDbFileHeaders.FIELD_PARAM_3.getName(),
                        Format.DATE_TIME_yyyy_MM_dd_HH_mm_ss)))
            .fieldParam4(fieldSet.readBoolean(ImportCsvToDbFileHeaders.FIELD_PARAM_4.getName()))
            .build();
    }

    @Override
    protected int getLinesToSkip() {
        return 1;
    }

    @Override
    protected String[] getHeaders() {
        return ImportCsvToDbFileHeaders.headers;
    }

    @Override
    protected Resource getResource() {
        return new InputStreamResource(new ByteArrayInputStream(storedResource.getPayload()));
    }
}
