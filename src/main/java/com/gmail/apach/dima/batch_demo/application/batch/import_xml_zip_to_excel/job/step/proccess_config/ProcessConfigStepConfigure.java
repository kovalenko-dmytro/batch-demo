package com.gmail.apach.dima.batch_demo.application.batch.import_xml_zip_to_excel.job.step.proccess_config;

import com.gmail.apach.dima.batch_demo.application.batch.import_xml_zip_to_excel.common.ImportXmlZipToExcelStep;
import com.gmail.apach.dima.batch_demo.application.batch.import_xml_zip_to_excel.job.step.proccess_config.task.ConfigCompositeItemProcessor;
import com.gmail.apach.dima.batch_demo.application.batch.import_xml_zip_to_excel.job.step.proccess_config.task.ConfigExcelItemWriter;
import com.gmail.apach.dima.batch_demo.application.batch.import_xml_zip_to_excel.job.step.proccess_config.task.ConfigXmlItemReader;
import com.gmail.apach.dima.batch_demo.application.batch.import_xml_zip_to_excel.model.ConfigExcelLineModel;
import com.gmail.apach.dima.batch_demo.application.batch.import_xml_zip_to_excel.model.ConfigXmlLineModel;
import com.gmail.apach.dima.batch_demo.application.core.job.configure.BaseBatchConfigure;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ProcessConfigStepConfigure {

    private final BaseBatchConfigure configure;

    private final ConfigXmlItemReader configXmlItemReader;
    private final ConfigCompositeItemProcessor configCompositeItemProcessor;
    private final ConfigExcelItemWriter configExcelItemWriter;

    @Bean
    @SuppressWarnings("unused")
    protected Step processConfigStep() {
        return new StepBuilder(ImportXmlZipToExcelStep.PROCESS_CONFIG.getName(), configure.getJobRepository())
            .<ConfigXmlLineModel, ConfigExcelLineModel>chunk(
                configure.getBatchConfigProperties().getBatchSize(),
                configure.getPlatformTransactionManager()
            )
            .reader(configXmlItemReader)
            .processor(configCompositeItemProcessor)
            .writer(configExcelItemWriter)
            .listener(configure.getLogStepExecutionListener())
            .exceptionHandler(configure.getBaseJobExceptionHandler())
            .build();
    }
}
