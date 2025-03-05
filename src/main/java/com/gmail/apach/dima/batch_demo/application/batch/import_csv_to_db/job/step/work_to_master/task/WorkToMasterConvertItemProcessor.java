package com.gmail.apach.dima.batch_demo.application.batch.import_csv_to_db.job.step.work_to_master.task;

import com.gmail.apach.dima.batch_demo.application.batch.import_csv_to_db.model.MasterModel;
import com.gmail.apach.dima.batch_demo.infrastructure.adapter.output.db.work.entity.WorkTableEntity;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class WorkToMasterConvertItemProcessor implements ItemProcessor<WorkTableEntity, MasterModel> {

    @NonNull
    @Override
    public MasterModel process(@NonNull WorkTableEntity item) {
        return MasterModel.builder()
            .column1(item.getFieldParam1())
            .column2(item.getFieldParam2())
            .column3(item.getFieldParam3())
            .column4(item.getFieldParam4())
            .build();
    }
}
