package com.gmail.apach.dima.batch_demo.application.batch.import_xml_zip_to_excel.job.step.proccess_config.task;

import com.gmail.apach.dima.batch_demo.application.batch.import_xml_zip_to_excel.model.ConfigExcelLineModel;
import com.gmail.apach.dima.batch_demo.application.batch.import_xml_zip_to_excel.model.ConfigXmlLineModel;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.support.CompositeItemProcessor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@RequiredArgsConstructor
public class ConfigCompositeItemProcessor
    extends CompositeItemProcessor<ConfigXmlLineModel, ConfigExcelLineModel> {

    private final ConfigConvertItemProcessor convertItemProcessor;
    private final ConfigValidateItemProcessor validateItemProcessor;

    @Override
    public void afterPropertiesSet() throws Exception {
        super.setDelegates(List.of(convertItemProcessor, validateItemProcessor));
        super.afterPropertiesSet();
    }
}
