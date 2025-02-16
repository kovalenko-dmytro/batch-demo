package com.gmail.apach.dima.batch_demo.core.job.import_example.job.step.file_to_work.task.reader;

import com.gmail.apach.dima.batch_demo.application.output.oss.AwsOssOutputPort;
import com.gmail.apach.dima.batch_demo.core.base.common.constant.Format;
import com.gmail.apach.dima.batch_demo.core.base.common.util.DateUtil;
import com.gmail.apach.dima.batch_demo.core.base.job.reader.CsvFileItemReader;
import com.gmail.apach.dima.batch_demo.core.base.model.job.Parameter;
import com.gmail.apach.dima.batch_demo.core.job.import_example.common.ExampleHeaders;
import com.gmail.apach.dima.batch_demo.core.job.import_example.model.ExampleLine;
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

    private final AwsOssOutputPort awsOssOutputPort;

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
                        Format.DATE_TIME_yyyy_MM_dd_HH_mm_ss)
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
    protected Resource getResource() {
        final var payload = awsOssOutputPort.get(fileStorageResource).getPayload();
        return new InputStreamResource(new ByteArrayInputStream(payload));
    }
}
