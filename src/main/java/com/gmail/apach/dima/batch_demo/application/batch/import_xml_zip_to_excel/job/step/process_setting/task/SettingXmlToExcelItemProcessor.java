package com.gmail.apach.dima.batch_demo.application.batch.import_xml_zip_to_excel.job.step.process_setting.task;

import com.gmail.apach.dima.batch_demo.application.batch.import_xml_zip_to_excel.mapper.XmlMapper;
import com.gmail.apach.dima.batch_demo.application.batch.import_xml_zip_to_excel.model.SettingExcelLine;
import com.gmail.apach.dima.batch_demo.application.batch.import_xml_zip_to_excel.model.SettingXmlLine;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@RequiredArgsConstructor
public class SettingXmlToExcelItemProcessor implements ItemProcessor<SettingXmlLine, SettingExcelLine> {

    private final XmlMapper xmlMapper;

    @NonNull
    @Override
    public SettingExcelLine process(@NonNull SettingXmlLine settingXmlLine) {
        return xmlMapper.toSettingExcelLine(settingXmlLine);
    }
}
