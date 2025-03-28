package com.gmail.apach.dima.batch_demo.application.batch.import_excel_to_csv.job.step.excel_to_csv.task;

import com.gmail.apach.dima.batch_demo.application.batch.import_excel_to_csv.common.CsvFileFieldNames;
import com.gmail.apach.dima.batch_demo.application.batch.import_excel_to_csv.common.CsvFileHeaders;
import com.gmail.apach.dima.batch_demo.application.batch.import_excel_to_csv.model.CsvLineModel;
import com.gmail.apach.dima.batch_demo.application.core.file.model.StoredResource;
import com.gmail.apach.dima.batch_demo.application.core.job.constant.JobExecutionContextKey;
import com.gmail.apach.dima.batch_demo.application.core.job.writer.CsvFileItemWriter;
import com.gmail.apach.dima.batch_demo.common.constant.Extension;
import com.gmail.apach.dima.batch_demo.common.util.FileUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.WritableResource;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@RequiredArgsConstructor
public class CsvItemWriter extends CsvFileItemWriter<CsvLineModel> implements StepExecutionListener {

    private String exportFileTempPath;

    @Override
    public void beforeStep(@NonNull StepExecution stepExecution) {
        final var storedResource = (StoredResource) stepExecution
            .getJobExecution()
            .getExecutionContext()
            .get(JobExecutionContextKey.STORED_RESOURCE);
        this.exportFileTempPath = FileUtil.createTempFile(storedResource.getFileName(), Extension.CSV);
        stepExecution
            .getJobExecution()
            .getExecutionContext()
            .put(JobExecutionContextKey.EXPORT_FILE_TEMP_PATH, exportFileTempPath);
    }

    @Override
    protected String[] getFieldNames() {
        return CsvFileFieldNames.names;
    }

    @Override
    protected String getHeadersRow() {
        return CsvFileHeaders.headersRow;
    }

    @Override
    protected WritableResource getResource() {
        return new FileSystemResource(exportFileTempPath);
    }
}
