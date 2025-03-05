package com.gmail.apach.dima.batch_demo.application.batch.import_xml_zip_to_excel.job.step.proccess_config.task;

import com.gmail.apach.dima.batch_demo.application.batch.import_xml_zip_to_excel.model.ConfigExcelLineModel;
import org.springframework.batch.item.validator.BeanValidatingItemProcessor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ConfigValidateItemProcessor extends BeanValidatingItemProcessor<ConfigExcelLineModel> {

    @Override
    public void afterPropertiesSet() throws Exception {
        super.setFilter(false);
        super.afterPropertiesSet();
    }
}
