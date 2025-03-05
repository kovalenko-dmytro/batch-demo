package com.gmail.apach.dima.batch_demo.application.batch.import_csv_to_db.job.step.work_to_master.task;

import com.gmail.apach.dima.batch_demo.application.batch.import_csv_to_db.model.MasterModel;
import com.gmail.apach.dima.batch_demo.infrastructure.adapter.output.db.work.entity.WorkTableEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.support.CompositeItemProcessor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@RequiredArgsConstructor
public class WorkToMasterCompositeItemProcessor extends CompositeItemProcessor<WorkTableEntity, MasterModel> {

    private final WorkToMasterConvertItemProcessor convertItemProcessor;
    private final WorkToMasterValidateItemProcessor validateItemProcessor;

    @Override
    public void afterPropertiesSet() throws Exception {
        super.setDelegates(List.of(convertItemProcessor, validateItemProcessor));
        super.afterPropertiesSet();
    }
}
