package com.gmail.apach.dima.batch_demo.application.batch.import_xml_zip_to_excel.job.step.process_setting;

import com.gmail.apach.dima.batch_demo.application.batch.import_xml_zip_to_excel.common.ImportXmlZipToExcelStep;
import com.gmail.apach.dima.batch_demo.application.batch.import_xml_zip_to_excel.job.step.process_setting.task.SettingCompositeItemProcessor;
import com.gmail.apach.dima.batch_demo.application.batch.import_xml_zip_to_excel.job.step.process_setting.task.SettingExcelItemWriter;
import com.gmail.apach.dima.batch_demo.application.batch.import_xml_zip_to_excel.job.step.process_setting.task.SettingXmlItemReader;
import com.gmail.apach.dima.batch_demo.application.batch.import_xml_zip_to_excel.model.SettingExcelLineModel;
import com.gmail.apach.dima.batch_demo.application.batch.import_xml_zip_to_excel.model.SettingXmlLineModel;
import com.gmail.apach.dima.batch_demo.application.core.job.configure.BaseBatchConfigure;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ProcessSettingStepConfigure {

    private final BaseBatchConfigure configure;

    private final SettingXmlItemReader settingXmlItemReader;
    private final SettingCompositeItemProcessor settingCompositeItemProcessor;
    private final SettingExcelItemWriter settingExcelItemWriter;

    @Bean
    @SuppressWarnings("unused")
    protected Step processSettingStep() {
        return new StepBuilder(ImportXmlZipToExcelStep.PROCESS_SETTING.getName(), configure.getJobRepository())
            .<SettingXmlLineModel, SettingExcelLineModel>chunk(
                configure.getBatchConfigProperties().getBatchSize(),
                configure.getPlatformTransactionManager()
            )
            .reader(settingXmlItemReader)
            .processor(settingCompositeItemProcessor)
            .writer(settingExcelItemWriter)
            .listener(configure.getLogStepExecutionListener())
            .exceptionHandler(configure.getBaseJobExceptionHandler())
            .build();
    }
}
