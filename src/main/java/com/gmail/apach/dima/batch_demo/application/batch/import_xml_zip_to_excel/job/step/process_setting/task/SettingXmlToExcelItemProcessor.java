package com.gmail.apach.dima.batch_demo.application.batch.import_xml_zip_to_excel.job.step.process_setting.task;

import com.gmail.apach.dima.batch_demo.application.batch.import_xml_zip_to_excel.mapper.XmlMapper;
import com.gmail.apach.dima.batch_demo.application.batch.import_xml_zip_to_excel.model.SettingExcelLineModel;
import com.gmail.apach.dima.batch_demo.application.batch.import_xml_zip_to_excel.model.SettingXmlLineModel;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@RequiredArgsConstructor
public class SettingXmlToExcelItemProcessor implements ItemProcessor<SettingXmlLineModel, SettingExcelLineModel> {

    private final XmlMapper xmlMapper;

    @NonNull
    @Override
    public SettingExcelLineModel process(@NonNull SettingXmlLineModel settingXmlLineModel) {
        return xmlMapper.toSettingExcelLine(settingXmlLineModel);
    }
}
