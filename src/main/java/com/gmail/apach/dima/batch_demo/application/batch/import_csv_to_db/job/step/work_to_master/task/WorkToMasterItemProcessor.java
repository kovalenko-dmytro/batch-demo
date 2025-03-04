package com.gmail.apach.dima.batch_demo.application.batch.import_csv_to_db.job.step.work_to_master.task;

import com.gmail.apach.dima.batch_demo.application.batch.import_csv_to_db.model.MasterModel;
import com.gmail.apach.dima.batch_demo.application.core.common.validator.FieldValidator;
import com.gmail.apach.dima.batch_demo.infrastructure.adapter.output.db.work.entity.WorkTableEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@RequiredArgsConstructor
public class WorkToMasterItemProcessor implements ItemProcessor<WorkTableEntity, MasterModel> {

    private final FieldValidator<MasterModel> validator;

    @NonNull
    @Override
    public MasterModel process(@NonNull WorkTableEntity work) throws Exception {
        MasterModel masterModel = MasterModel.builder()
            .column1(work.getFieldParam1())
            .column2(work.getFieldParam2())
            .column3(work.getFieldParam3())
            .column4(work.getFieldParam4())
            .build();
        validator.validate(masterModel);
        return masterModel;
    }
}
