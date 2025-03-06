package com.gmail.apach.dima.batch_demo.application.batch.import_xml_zip_to_excel.job.step.process_template;

import com.gmail.apach.dima.batch_demo.application.batch.import_xml_zip_to_excel.common.ImportXmlZipToExcelStep;
import com.gmail.apach.dima.batch_demo.application.batch.import_xml_zip_to_excel.job.step.process_template.task.TemplateCompositeItemProcessor;
import com.gmail.apach.dima.batch_demo.application.batch.import_xml_zip_to_excel.job.step.process_template.task.TemplateExcelItemWriter;
import com.gmail.apach.dima.batch_demo.application.batch.import_xml_zip_to_excel.job.step.process_template.task.TemplateXmlItemReader;
import com.gmail.apach.dima.batch_demo.application.batch.import_xml_zip_to_excel.model.TemplateExcelLineModel;
import com.gmail.apach.dima.batch_demo.application.batch.import_xml_zip_to_excel.model.TemplateXmlLineModel;
import com.gmail.apach.dima.batch_demo.application.core.job.configure.BaseBatchConfigure;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ProcessTemplateStepConfigure {

    private final BaseBatchConfigure configure;

    private final TemplateXmlItemReader templateXmlItemReader;
    private final TemplateCompositeItemProcessor templateCompositeItemProcessor;
    private final TemplateExcelItemWriter csvItemWriter;

    @Bean
    @SuppressWarnings("unused")
    protected Step processTemplateStep() {
        return new StepBuilder(ImportXmlZipToExcelStep.PROCESS_TEMPLATE.getName(), configure.getJobRepository())
            .<TemplateXmlLineModel, TemplateExcelLineModel>chunk(
                configure.getBatchConfigProperties().getBatchSize(),
                configure.getPlatformTransactionManager())
            .reader(templateXmlItemReader)
            .processor(templateCompositeItemProcessor)
            .writer(csvItemWriter)
            .listener(configure.getLogStepExecutionListener())
            .exceptionHandler(configure.getBaseJobExceptionHandler())
            .build();
    }
}
