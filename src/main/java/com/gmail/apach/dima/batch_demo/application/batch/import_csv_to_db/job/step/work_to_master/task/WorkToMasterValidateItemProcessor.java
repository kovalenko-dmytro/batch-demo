package com.gmail.apach.dima.batch_demo.application.batch.import_csv_to_db.job.step.work_to_master.task;

import com.gmail.apach.dima.batch_demo.application.batch.import_csv_to_db.model.MasterModel;
import org.springframework.batch.item.validator.BeanValidatingItemProcessor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class WorkToMasterValidateItemProcessor extends BeanValidatingItemProcessor<MasterModel> {

    @Override
    public void afterPropertiesSet() throws Exception {
        super.setFilter(false);
        super.afterPropertiesSet();
    }
}
