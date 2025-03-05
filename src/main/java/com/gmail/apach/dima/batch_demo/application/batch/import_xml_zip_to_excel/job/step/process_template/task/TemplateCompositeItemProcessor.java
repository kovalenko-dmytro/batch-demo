package com.gmail.apach.dima.batch_demo.application.batch.import_xml_zip_to_excel.job.step.process_template.task;

import com.gmail.apach.dima.batch_demo.application.batch.import_xml_zip_to_excel.model.TemplateExcelLineModel;
import com.gmail.apach.dima.batch_demo.application.batch.import_xml_zip_to_excel.model.TemplateXmlLineModel;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.support.CompositeItemProcessor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@RequiredArgsConstructor
public class TemplateCompositeItemProcessor
    extends CompositeItemProcessor<TemplateXmlLineModel, TemplateExcelLineModel> {

    private final TemplateConvertItemProcessor convertItemProcessor;
    private final TemplateValidateItemProcessor validateItemProcessor;

    @Override
    public void afterPropertiesSet() throws Exception {
        super.setDelegates(List.of(convertItemProcessor, validateItemProcessor));
        super.afterPropertiesSet();
    }
}
