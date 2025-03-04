package com.gmail.apach.dima.batch_demo.application.batch.import_csv_to_db.job.step.file_to_work.task;

import com.gmail.apach.dima.batch_demo.application.batch.import_csv_to_db.model.CsvLineModel;
import com.gmail.apach.dima.batch_demo.application.batch.import_csv_to_db.model.WorkModel;
import com.gmail.apach.dima.batch_demo.application.core.common.validator.FieldValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@RequiredArgsConstructor
public class FileToWorkItemProcessor implements ItemProcessor<CsvLineModel, WorkModel> {

    private final FieldValidator<CsvLineModel> validator;

    @NonNull
    @Override
    public WorkModel process(@NonNull CsvLineModel csvLineModel) throws Exception {
        validator.validate(csvLineModel);
        return WorkModel.builder()
            .fieldParam1(csvLineModel.fieldParam1())
            .fieldParam2(csvLineModel.fieldParam2())
            .fieldParam3(csvLineModel.fieldParam3())
            .fieldParam4(csvLineModel.fieldParam4())
            .build();
    }
}
