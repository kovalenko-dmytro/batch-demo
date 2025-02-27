package com.gmail.apach.dima.batch_demo.application.batch.import_xml_zip_to_excel.job.step.proccess_config.task;

import com.gmail.apach.dima.batch_demo.application.batch.import_xml_zip_to_excel.mapper.XmlMapper;
import com.gmail.apach.dima.batch_demo.application.batch.import_xml_zip_to_excel.model.ConfigExcelLine;
import com.gmail.apach.dima.batch_demo.application.batch.import_xml_zip_to_excel.model.ConfigXmlLine;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@RequiredArgsConstructor
public class ConfigXmlToExcelItemProcessor implements ItemProcessor<ConfigXmlLine, ConfigExcelLine> {

    private final XmlMapper xmlMapper;

    @NonNull
    @Override
    public ConfigExcelLine process(@NonNull ConfigXmlLine configXmlLine) {
        return xmlMapper.toConfigExcelLine(configXmlLine);
    }
}
