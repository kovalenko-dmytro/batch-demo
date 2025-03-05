package com.gmail.apach.dima.batch_demo.application.batch.import_xml_zip_to_excel.job.step.process_setting.task;

import com.gmail.apach.dima.batch_demo.application.batch.import_xml_zip_to_excel.model.SettingExcelLineModel;
import com.gmail.apach.dima.batch_demo.application.batch.import_xml_zip_to_excel.model.SettingXmlLineModel;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SettingConvertItemProcessor implements ItemProcessor<SettingXmlLineModel, SettingExcelLineModel> {

    @NonNull
    @Override
    public SettingExcelLineModel process(@NonNull SettingXmlLineModel item) {
        return SettingExcelLineModel.builder()
            .activeFlag(item.getActiveFlag())
            .approveFlag(item.getApproveFlag())
            .build();
    }
}
