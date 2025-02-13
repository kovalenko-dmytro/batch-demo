package com.gmail.apach.dima.batch_demo.example.reader;

import com.gmail.apach.dima.batch_demo.core.common.DateConstant;
import com.gmail.apach.dima.batch_demo.core.exception.ObjectStorageException;
import com.gmail.apach.dima.batch_demo.core.job.reader.CsvFileItemReader;
import com.gmail.apach.dima.batch_demo.core.model.batch.Parameter;
import com.gmail.apach.dima.batch_demo.core.service.oss.ObjectStorageService;
import com.gmail.apach.dima.batch_demo.core.util.DateUtil;
import com.gmail.apach.dima.batch_demo.example.common.ExampleHeaders;
import com.gmail.apach.dima.batch_demo.example.model.ExampleLine;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@RequiredArgsConstructor
public class ExampleReader extends CsvFileItemReader<ExampleLine> implements StepExecutionListener {

    private final ObjectStorageService awsS3StorageService;

    private String fileStorageResource;

    @Override
    public void beforeStep(@NonNull StepExecution stepExecution) {
        this.fileStorageResource = stepExecution
            .getJobExecution()
            .getJobParameters()
            .getString(Parameter.FILE_STORAGE_RESOURCE.getArg());
    }

    @Override
    protected FieldSetMapper<ExampleLine> fieldSetMapper() {
        return fieldSet -> ExampleLine.builder()
            .stringColumn(fieldSet.readString(ExampleHeaders.HEADER_STRING.getValue()))
            .integerColumn(fieldSet.readInt(ExampleHeaders.HEADER_INTEGER.getValue()))
            .dateTimeColumn(DateUtil.toLocalDateTime(
                    fieldSet.readDate(
                        ExampleHeaders.HEADER_DATE_TIME.getValue(),
                        DateConstant.DATE_TIME_PATTERN)
                )
            )
            .booleanColumn(fieldSet.readBoolean(ExampleHeaders.HEADER_BOOLEAN.getValue()))
            .build();
    }

    @Override
    protected int getLinesToSkip() {
        return 1;
    }

    @Override
    protected String[] getHeaders() {
        return ExampleHeaders.headers;
    }

    @Override
    protected Resource getResource() throws ObjectStorageException {
        final var payload = awsS3StorageService.get(fileStorageResource).getPayload();
        return new InputStreamResource(new ByteArrayInputStream(payload));
    }
}
