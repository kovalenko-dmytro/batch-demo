package com.gmail.apach.dima.batch_demo.application.batch.import_csv_to_db.job.step.file_to_work.task;

import com.gmail.apach.dima.batch_demo.application.batch.import_csv_to_db.model.CsvLineModel;
import com.gmail.apach.dima.batch_demo.application.batch.import_csv_to_db.model.WorkModel;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class FileToWorkConvertItemProcessor implements ItemProcessor<CsvLineModel, WorkModel> {

    @NonNull
    @Override
    public WorkModel process(@NonNull CsvLineModel item) {
        return WorkModel.builder()
            .fieldParam1(item.fieldParam1())
            .fieldParam2(item.fieldParam2())
            .fieldParam3(item.fieldParam3())
            .fieldParam4(item.fieldParam4())
            .build();
    }
}
