package com.gmail.apach.dima.batch_demo.application.batch.import_xml_zip_to_excel.job.step.process_template.task;

import com.gmail.apach.dima.batch_demo.application.batch.import_xml_zip_to_excel.mapper.XmlMapper;
import com.gmail.apach.dima.batch_demo.application.batch.import_xml_zip_to_excel.model.TemplateExcelLine;
import com.gmail.apach.dima.batch_demo.application.batch.import_xml_zip_to_excel.model.TemplateXmlLine;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@RequiredArgsConstructor
public class TemplateXmlToExcelItemProcessor implements ItemProcessor<TemplateXmlLine, TemplateExcelLine> {

    private final XmlMapper xmlMapper;

    @NonNull
    @Override
    public TemplateExcelLine process(@NonNull TemplateXmlLine templateXmlLine) {
        return xmlMapper.toTemplateExcelLine(templateXmlLine);
    }
}
