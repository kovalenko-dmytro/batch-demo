package com.gmail.apach.dima.batch_demo.application.batch.import_xml_zip_to_excel.job.step.proccess_config.task;

import com.gmail.apach.dima.batch_demo.application.batch.import_xml_zip_to_excel.common.ZippedFileName;
import com.gmail.apach.dima.batch_demo.application.batch.import_xml_zip_to_excel.model.ConfigXmlLine;
import com.gmail.apach.dima.batch_demo.application.core.job.constant.JobExecutionContextKey;
import com.gmail.apach.dima.batch_demo.application.core.job.reader.XmlFileItemReader;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.FileUrlResource;
import org.springframework.core.io.Resource;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.nio.file.Path;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@RequiredArgsConstructor
public class ConfigXmlItemReader extends XmlFileItemReader<ConfigXmlLine> implements StepExecutionListener {

    private String sourceFilePath;

    @Override
    public void beforeStep(@NonNull StepExecution stepExecution) {
        final var dirPath = (String) stepExecution
            .getJobExecution()
            .getExecutionContext()
            .get(JobExecutionContextKey.TEMP_DIR_PATH);
        this.sourceFilePath = Path.of(dirPath, ZippedFileName.CONFIG.getName()).toString();
    }

    @Override
    protected Class<?> getBoundedClass() {
        return ConfigXmlLine.class;
    }

    @Override
    protected String[] getFragmentRootElements() {
        return new String[]{"config"};
    }

    @Override
    protected Resource getResource() throws Exception {
        return new FileUrlResource(sourceFilePath);
    }
}
