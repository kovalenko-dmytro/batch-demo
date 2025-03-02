package com.gmail.apach.dima.batch_demo.application.batch.import_xml_zip_to_excel.job.step.proccess_config;

import com.gmail.apach.dima.batch_demo.application.batch.import_xml_zip_to_excel.common.ImportXmlZipToExcelStep;
import com.gmail.apach.dima.batch_demo.application.batch.import_xml_zip_to_excel.job.step.proccess_config.task.ConfigExcelItemWriter;
import com.gmail.apach.dima.batch_demo.application.batch.import_xml_zip_to_excel.job.step.proccess_config.task.ConfigXmlItemReader;
import com.gmail.apach.dima.batch_demo.application.batch.import_xml_zip_to_excel.job.step.proccess_config.task.ConfigXmlToExcelItemProcessor;
import com.gmail.apach.dima.batch_demo.application.batch.import_xml_zip_to_excel.model.ConfigExcelLineModel;
import com.gmail.apach.dima.batch_demo.application.batch.import_xml_zip_to_excel.model.ConfigXmlLineModel;
import com.gmail.apach.dima.batch_demo.application.core.job.handler.BaseJobExceptionHandler;
import com.gmail.apach.dima.batch_demo.application.core.job.listener.LogStepExecutionListener;
import com.gmail.apach.dima.batch_demo.infrastructure.common.batch.BatchConfigProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@RequiredArgsConstructor
public class ProcessConfigStepConfigure {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;
    private final BatchConfigProperties batchConfigProperties;
    private final ConfigXmlItemReader configXmlItemReader;
    private final ConfigXmlToExcelItemProcessor configXmlToExcelItemProcessor;
    private final ConfigExcelItemWriter configExcelItemWriter;
    private final LogStepExecutionListener logStepExecutionListener;
    private final BaseJobExceptionHandler exceptionHandler;

    @Bean
    @SuppressWarnings("unused")
    protected Step processConfigStep() {
        return new StepBuilder(ImportXmlZipToExcelStep.PROCESS_CONFIG.getName(), jobRepository)
            .<ConfigXmlLineModel, ConfigExcelLineModel>chunk(batchConfigProperties.getBatchSize(), transactionManager)
            .reader(configXmlItemReader)
            .processor(configXmlToExcelItemProcessor)
            .writer(configExcelItemWriter)
            .listener(logStepExecutionListener)
            .exceptionHandler(exceptionHandler)
            .build();
    }
}
