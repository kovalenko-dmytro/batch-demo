package com.gmail.apach.dima.batch_demo.application.batch.import_xml_zip_to_excel.job.step.proccess_config.task;

import com.gmail.apach.dima.batch_demo.application.batch.import_xml_zip_to_excel.model.ConfigExcelLineModel;
import com.gmail.apach.dima.batch_demo.application.batch.import_xml_zip_to_excel.model.ConfigXmlLineModel;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ConfigConvertItemProcessor implements ItemProcessor<ConfigXmlLineModel, ConfigExcelLineModel> {

    @NonNull
    @Override
    public ConfigExcelLineModel process(@NonNull ConfigXmlLineModel item) {
        return ConfigExcelLineModel.builder()
            .locale(item.getLocale())
            .version(item.getVersion())
            .build();
    }
}
