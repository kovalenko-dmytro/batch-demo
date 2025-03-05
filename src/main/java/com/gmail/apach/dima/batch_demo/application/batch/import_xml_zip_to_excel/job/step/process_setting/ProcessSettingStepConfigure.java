package com.gmail.apach.dima.batch_demo.application.batch.import_xml_zip_to_excel.job.step.process_setting;

import com.gmail.apach.dima.batch_demo.application.batch.import_xml_zip_to_excel.common.ImportXmlZipToExcelStep;
import com.gmail.apach.dima.batch_demo.application.batch.import_xml_zip_to_excel.job.step.process_setting.task.SettingCompositeItemProcessor;
import com.gmail.apach.dima.batch_demo.application.batch.import_xml_zip_to_excel.job.step.process_setting.task.SettingExcelItemWriter;
import com.gmail.apach.dima.batch_demo.application.batch.import_xml_zip_to_excel.job.step.process_setting.task.SettingXmlItemReader;
import com.gmail.apach.dima.batch_demo.application.batch.import_xml_zip_to_excel.model.SettingExcelLineModel;
import com.gmail.apach.dima.batch_demo.application.batch.import_xml_zip_to_excel.model.SettingXmlLineModel;
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
public class ProcessSettingStepConfigure {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;
    private final BatchConfigProperties batchConfigProperties;
    private final SettingXmlItemReader settingXmlItemReader;
    private final SettingCompositeItemProcessor settingCompositeItemProcessor;
    private final SettingExcelItemWriter settingExcelItemWriter;
    private final LogStepExecutionListener logStepExecutionListener;
    private final BaseJobExceptionHandler exceptionHandler;

    @Bean
    @SuppressWarnings("unused")
    protected Step processSettingStep() {
        return new StepBuilder(ImportXmlZipToExcelStep.PROCESS_SETTING.getName(), jobRepository)
            .<SettingXmlLineModel, SettingExcelLineModel>chunk(batchConfigProperties.getBatchSize(), transactionManager)
            .reader(settingXmlItemReader)
            .processor(settingCompositeItemProcessor)
            .writer(settingExcelItemWriter)
            .listener(logStepExecutionListener)
            .exceptionHandler(exceptionHandler)
            .build();
    }
}
