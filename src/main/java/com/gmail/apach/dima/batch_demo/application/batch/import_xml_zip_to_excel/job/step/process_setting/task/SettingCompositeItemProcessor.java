package com.gmail.apach.dima.batch_demo.application.batch.import_xml_zip_to_excel.job.step.process_setting.task;

import com.gmail.apach.dima.batch_demo.application.batch.import_xml_zip_to_excel.model.SettingExcelLineModel;
import com.gmail.apach.dima.batch_demo.application.batch.import_xml_zip_to_excel.model.SettingXmlLineModel;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.support.CompositeItemProcessor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@RequiredArgsConstructor
public class SettingCompositeItemProcessor
    extends CompositeItemProcessor<SettingXmlLineModel, SettingExcelLineModel> {

    private final SettingConvertItemProcessor convertItemProcessor;
    private final SettingValidateItemProcessor validateItemProcessor;

    @Override
    public void afterPropertiesSet() throws Exception {
        super.setDelegates(List.of(convertItemProcessor, validateItemProcessor));
        super.afterPropertiesSet();
    }
}
