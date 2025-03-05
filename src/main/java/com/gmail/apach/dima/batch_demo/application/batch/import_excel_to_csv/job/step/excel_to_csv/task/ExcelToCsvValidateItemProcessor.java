package com.gmail.apach.dima.batch_demo.application.batch.import_excel_to_csv.job.step.excel_to_csv.task;

import com.gmail.apach.dima.batch_demo.application.batch.import_excel_to_csv.model.ExcelLineModel;
import org.springframework.batch.item.validator.BeanValidatingItemProcessor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ExcelToCsvValidateItemProcessor extends BeanValidatingItemProcessor<ExcelLineModel> {

    @Override
    public void afterPropertiesSet() throws Exception {
        super.setFilter(false);
        super.afterPropertiesSet();
    }
}
