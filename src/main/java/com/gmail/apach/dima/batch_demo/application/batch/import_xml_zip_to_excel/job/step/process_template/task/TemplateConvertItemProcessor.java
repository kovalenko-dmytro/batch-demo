package com.gmail.apach.dima.batch_demo.application.batch.import_xml_zip_to_excel.job.step.process_template.task;

import com.gmail.apach.dima.batch_demo.application.batch.import_xml_zip_to_excel.model.TemplateExcelLineModel;
import com.gmail.apach.dima.batch_demo.application.batch.import_xml_zip_to_excel.model.TemplateXmlLineModel;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class TemplateConvertItemProcessor implements ItemProcessor<TemplateXmlLineModel, TemplateExcelLineModel> {

    @NonNull
    @Override
    public TemplateExcelLineModel process(@NonNull TemplateXmlLineModel item) {
        return TemplateExcelLineModel.builder()
            .name(item.getName())
            .description(item.getDescription())
            .code(item.getCode())
            .enabled(item.getEnabled())
            .build();
    }
}
